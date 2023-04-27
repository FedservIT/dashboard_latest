package com.fedserv.pulse_dashboard.service;

import com.fedserv.pulse_dashboard.model.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class NewsService {
	public Object getOutput() throws Exception {
		String cat = "TopStories";
		String url = "https://www.business-standard.com/rss/home_page_top_stories.rss";
		JSONObject newsstatus = new JSONObject();
		URL u = new URL(url);
		HttpURLConnection con = (HttpURLConnection) u.openConnection();
		con.addRequestProperty("User-Agent", "Mozilla/4.0");
		BufferedReader br = null;
		String response = new String();
		if (con.getResponseCode() == 200) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String strCurrentLine;
			while ((strCurrentLine = br.readLine()) != null) {

				if (strCurrentLine.contains("media:content")) {

					int stIndex = strCurrentLine.indexOf("url=");
					strCurrentLine = strCurrentLine.substring(stIndex);
					strCurrentLine = strCurrentLine.replaceAll("/>", "");
					String data[] = strCurrentLine.split(" ");
					strCurrentLine = data[0].replaceAll("url=", "");
					strCurrentLine = strCurrentLine.replaceAll("\"", "");
					strCurrentLine = "<mediacontent><![CDATA[" + strCurrentLine + "]]></mediacontent>";

				}

				if (strCurrentLine.contains("media:thumbnail")) {
					int stIndex = strCurrentLine.indexOf("url=");
					strCurrentLine = strCurrentLine.substring(stIndex);
					strCurrentLine = strCurrentLine.replaceAll("/>", "");
					String data[] = strCurrentLine.split(" ");
					strCurrentLine = data[0].replaceAll("url=", "");
					strCurrentLine = strCurrentLine.replaceAll("\"", "");
					strCurrentLine = "<mediathumbnail>" + strCurrentLine + "</mediathumbnail>";

				}

				if (strCurrentLine.contains("media:description")) {
					strCurrentLine = "<mediadescription>" + strCurrentLine + "</mediadescription>";
				}
				if (strCurrentLine.contains("media:title")) {
					// System.out.println("Inside media:title"+strCurrentLine);
					strCurrentLine = "<mediatitile>" + strCurrentLine + "</mediatitile>";
				}

				response += strCurrentLine;
			}

		}
		con.disconnect();

		BufferedWriter writer = new BufferedWriter(new FileWriter("temp.xml"));
		writer.write(response);

		writer.close();

		RSSFeedParser parser = new RSSFeedParser("temp.xml");
		Feed feed = parser.readFeed();
		JSONObject objCat = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		int i = 0;
		if (cat.contentEquals("TopStories")) {
			for (FeedMessage message : feed.getMessages()) {
				if (cat.contentEquals("TopStories")) {
					i++;
					JSONObject obj = new JSONObject();
					obj.put("title", message.getTitle());
					obj.put("description", message.getDescription());
					obj.put("link", message.getLink());
					// obj.put("pubdate", message.getPubDate());
					obj.put("image", message.getImage());
					obj.put("guid", message.getGuid());
					// obj.put("media", message.getMedia_content());
					obj.put("media", message.getImage());
					obj.put("mediathumbnail", message.getMedia_thumbnail());
					obj.put("mediacredit", message.getMedia_credit());
					obj.put("mediatitle", message.getMedia_title());
					obj.put("mediadescription", message.getMedia_decription());
					// obj.put("lastBuildDate", message.getLastbuilddate());
					System.out.println(message.getLastbuilddate());

					String myDate = message.getLastbuilddate();
					String strMnth = myDate.substring(8, 11);
					System.out.println("Month" + strMnth);
					String day = myDate.substring(5, 7);
					String year = myDate.substring(12, 16);
					String time = myDate.substring(17, 25);
					DateFormat df1 = new SimpleDateFormat("dd/MMM/yyyy"); // for parsing input
					DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy"); // for formatting output
					String inputDate = day + '/' + strMnth + '/' + year;
					Date d = df1.parse(inputDate);
					String outputDate = df2.format(d);
					String strDate = outputDate + ' ' + time;
					obj.put("lastBuildDate", strDate);
					obj.put("pubdate", strDate);
					System.out.println("---TopStories--->" + strDate);
					jsonArr.put(obj);
				}
			}
		}

		objCat.put(cat, jsonArr);
		// System.out.println("news data are"+objCat);
		FirebaseModel firebaseModel = new FirebaseModel();
		firebaseModel.setData(objCat.toString());
		System.out.println(firebaseModel);
		Firestore firestoreDb = FirestoreClient.getFirestore();
		Firestore secondDB = FirestoreClient.getFirestore(com.fedserv.pulse_dashboard.service.MySecondaryProject.getInstance());
		ApiFuture<WriteResult> collectionsApiFuture;
		ApiFuture<WriteResult> collectionsApiFuture1;
		try {
			collectionsApiFuture = firestoreDb.collection("newscenter_test").document("TopStories").set(firebaseModel);
			System.out.println("saving News data to firebase" + collectionsApiFuture);
			collectionsApiFuture1 = secondDB.collection("newscenter_test").document("TopStories").set(firebaseModel);
			System.out.println("saving News data to firebase" + collectionsApiFuture1);
			return collectionsApiFuture.get().getUpdateTime().toString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsstatus;
	}

}
