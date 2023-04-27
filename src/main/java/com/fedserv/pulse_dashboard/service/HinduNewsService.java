package com.fedserv.pulse_dashboard.service;

//import com.federal.pulseNews.Model.Feed;
//import com.federal.pulseNews.Model.FeedMessage;
//import com.federal.pulseNews.Model.FirebaseModel;
//import com.fedserv.pulse_dashboard.firebvaseinitilization.FirebaseInitilization;
import com.fedserv.pulse_dashboard.model.*;
import com.fedserv.pulse_dashboard.model.FirebaseModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class HinduNewsService {
	public Object getOutput() throws Exception {
		String cat = "HinduNews";
		// String url =
		// "https://www.business-standard.com/rss/home_page_top_stories.rss";
		String url = "https://www.thehindu.com/feeder/default.rss";
		JSONObject newsstatus = new JSONObject();
		JSONObject data1 = new JSONObject();
		JSONObject field = new JSONObject();
		JSONObject Stringvalue = new JSONObject();
		URL u = new URL(url);
		HttpURLConnection con = (HttpURLConnection) u.openConnection();
		BufferedReader br = null;
		String response = new String();
		if (con.getResponseCode() == 200) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String strCurrentLine;
			while ((strCurrentLine = br.readLine()) != null) {
				response += strCurrentLine;
			}

		} 
		con.disconnect();

		BufferedWriter writer = new BufferedWriter(new FileWriter("temp.xml"));
		writer.write(response);

		writer.close();

		com.fedserv.pulse_dashboard.service.RSSFeedParserHinduNews parser = new com.fedserv.pulse_dashboard.service.RSSFeedParserHinduNews("temp.xml");
		System.out.println("parser data"+parser.readFeed());
		Feed feed = parser.readFeed();
		JSONObject objCat = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		int i = 0;
		if (cat.contentEquals("HinduNews")) {
			for (FeedMessage message : feed.getMessages()) {
				if (cat.contentEquals("HinduNews")) {
					i++;
					JSONObject obj = new JSONObject();
					obj.put("title", message.getTitle());
					obj.put("description", message.getDescription());
					obj.put("link", message.getLink());
					obj.put("pubdate", message.getPubDate());
					obj.put("author", message.getAuthor());
					obj.put("guid", message.getGuid());
					obj.put("media", message.getMedia_content());
					// obj.put("media", message.getImage());
					obj.put("mediathumbnail", message.getMedia_thumbnail());
					obj.put("mediacredit", message.getMedia_credit());
					obj.put("mediatitle", message.getMedia_title());
					obj.put("mediadescription", message.getMedia_decription());
					obj.put("lastBuildDate", message.getLastbuilddate());
					jsonArr.put(obj);
				}
			}
		}
		objCat.put(cat, jsonArr);
		// System.out.println("news data are"+objCat);
		FirebaseModel firebaseModel = new FirebaseModel();
		firebaseModel.setData(objCat.toString());
		// System.out.println(firebaseModel);
		Firestore firestoreDb = FirestoreClient.getFirestore();
//		FirebaseInitilization firebaseInitilization=new FirebaseInitilization();
		Firestore secondDB = FirestoreClient.getFirestore(MySecondaryProject.getInstance());
		ApiFuture<WriteResult> collectionsApiFuture;
		ApiFuture<WriteResult> collectionsApiFuture1;
		try {
			collectionsApiFuture = firestoreDb.collection("newscenter_test2").document("HinduNews").set(firebaseModel);
			System.out.println("saving HinduNews data to firebase" + collectionsApiFuture);
			collectionsApiFuture1 = secondDB.collection("newscenter_test2").document("HinduNews").set(firebaseModel);
			System.out.println("saving HinduNews data to firebase" + collectionsApiFuture1);
			return collectionsApiFuture.get().getUpdateTime().toString();
			// newsstatus.put("updatetime",
			// collectionsApiFuture.get().getUpdateTime().toString());
			// return newsstatus;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsstatus;
	}
}
