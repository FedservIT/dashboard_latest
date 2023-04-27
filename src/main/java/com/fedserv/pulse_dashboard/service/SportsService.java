package com.fedserv.pulse_dashboard.service;

//import com.federal.pulseNews.Model.*;
//import com.fedserv.pulse_dashboard.firebvaseinitilization.FirebaseInitilization;
import com.fedserv.pulse_dashboard.model.*;
import com.fedserv.pulse_dashboard.model.FeedMarkets;
import com.fedserv.pulse_dashboard.model.FeedMessage;
import com.fedserv.pulse_dashboard.model.FirebaseModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class SportsService {

	public Object getOutput() throws Exception {
		String cat = "Sports";
		String url = "https://timesofindia.indiatimes.com/rssfeeds/4719148.cms";
		URL url1 = new URL(url);
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();

		JSONObject newsstatus = new JSONObject();
		BufferedReader br = null;
		BufferedReader br1 = null;
		String response = new String();
		if (con.getResponseCode() == 200) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			br1 = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String strCurrentLine;
			String strCurrentLine1 = "";
			while ((strCurrentLine = br.readLine()) != null) {
				strCurrentLine1 = br1.readLine();

				response += strCurrentLine;
			}
		}

		BufferedWriter writer = new BufferedWriter(new FileWriter("temp.xml"));
		writer.write(response);
		//System.out.println("Response is++" + response);
		writer.close();
		RSSFeedParserSports parsersports = new RSSFeedParserSports("temp.xml");
		JSONObject data1 = new JSONObject();
		JSONObject Stringvalue = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		int i = 0;
		if (cat.contentEquals("Sports")) {
			FeedMarkets feedsports = parsersports.readFeed();
			for (FeedMessage message : feedsports.getMessages()) {
				i++;
				
				JSONObject obj = new JSONObject();
				obj.put("title", message.getTitle());
				obj.put("description", message.getImage());
				String media = message.getDescription().replaceAll(" ", "");
				String[] subdesc = media.split("src=");

				for (int j = 1; j < subdesc.length; j++) {
					media = subdesc[j];
					
					if (media != null && media.length() > 0 && media.charAt(media.length() - 1) == '/') {
						media = media.substring(0, media.length() - 1);
						media.split("");
					}
					
					media.replaceAll("\"", "");

				}
				obj.put("media", media.replaceAll("\"", ""));
				obj.put("link", message.getLink());
				String myDate = message.getLastbuilddate();
				String strMnth    = myDate.substring(5,7);
				System.out.println("Month"+strMnth);
				String day        = myDate.substring(8,10);
				String year       = myDate.substring(0,4);
				String time       = myDate.substring(11,20);
				time = time.replaceAll("[+]", " ");
				String inputDate = day +'/'+strMnth+'/'+year;
				String strDate = inputDate+' '+time;
				System.out.println("---Sports--->"+strDate );
				obj.put("pubdate", strDate);
				obj.put("lastBuildDate", strDate);
				jsonArr.put(obj);

			}
		}

		int index = 0;
		int len = jsonArr.length();
		//System.out.println("fields>>>>>" + len);
		JSONObject obj2 = new JSONObject();
		for (int k = 0; k < len; k++) {
			if (k == index) {
				jsonArr.remove(k);
			}
			if (k != index) {
				obj2.put(cat, jsonArr);
				System.out.println("json array" + jsonArr);
				Stringvalue.put("stringValue", obj2.toString());
			//	System.out.println("value of string" + Stringvalue);

			}
			data1.put("data", Stringvalue);
		}

		FirebaseModel firebaseModel = new FirebaseModel();
		firebaseModel.setData(obj2.toString().toString());
		//System.out.println(firebaseModel);
		Firestore firestoreDb = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture;
		ApiFuture<WriteResult> collectionsApiFuture1;
		Firestore secondDB = FirestoreClient.getFirestore(MySecondaryProject.getInstance());
		try {
			collectionsApiFuture = firestoreDb.collection("newscenter_test").document("Sports").set(firebaseModel);
			System.out.println("saving sports data to firebase" + collectionsApiFuture);
			collectionsApiFuture1 = secondDB.collection("newscenter_test")
					.document("Sports").set(firebaseModel);
			System.out.println("saving News data to firebase"+collectionsApiFuture1);
			return collectionsApiFuture.get().getUpdateTime().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("fsgfgffg"+newsstatus.toString());
		return newsstatus;
	}

}
