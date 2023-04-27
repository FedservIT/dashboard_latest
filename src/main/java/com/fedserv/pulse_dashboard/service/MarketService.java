package com.fedserv.pulse_dashboard.service;

//import com.federal.pulseNews.Model.FeedMarkets;
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
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class MarketService {

	public Object getOutput() throws Exception {
		String cat = "Markets";
		String url = "https://economictimes.indiatimes.com/markets/rssfeeds/1977021501.cms";
		URL url1 = new URL(url);
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();
		JSONObject newsstatus = new JSONObject();
		JSONObject field = new JSONObject();
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
		RSSFeedParserMarkets parsermarkets = new RSSFeedParserMarkets("temp.xml");
		JSONArray jsonArr = new JSONArray();
		int i = 0;
		if (cat.contentEquals("Markets")) {
			if (cat.contentEquals("Markets")) {
				//System.out.println("Parsing data are" + parsermarkets);
				FeedMarkets feedmarkets = parsermarkets.readFeed();
				for (FeedMessage message : feedmarkets.getMessages()) {
					// System.out.println("Category is"+cat);
					if (!message.getImage().isEmpty()) {
						i++;
						JSONObject obj = new JSONObject();
						obj.put("title", message.getTitle());
						if (message.getDescription().equals("<")) {	
							obj.put("description", "");
						} else {
							obj.put("description", message.getDescription());
							obj.put("description", "");
						}
						obj.put("link", message.getLink());
						//obj.put("pubdate", message.getPubDate());
						obj.put("guid", message.getGuid());
						//obj.put("lastBuildDate", message.getLastbuilddate());
						obj.put("media", message.getImage());
						obj.put("language", message.getLanguage());
						obj.put("docs", message.getDocs());
						obj.put("copyright", message.getCopyright());
						String myDate = message.getLastbuilddate();
						String strMnth    = myDate.substring(5,7);
						System.out.println("Month"+strMnth);
						String day        = myDate.substring(8,10);
						String year       = myDate.substring(0,4);
						String time       = myDate.substring(11,20);
						time = time.replaceAll("[+]", " ");
						String inputDate = day +'/'+strMnth+'/'+year;
						String strDate = inputDate+' '+time;
						System.out.println("---Markets--->"+strDate );
						obj.put("pubdate", strDate);
						obj.put("lastBuildDate", strDate);

						jsonArr.put(obj);

					}
				}
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

			}
			
		}
		FirebaseModel firebaseModel = new FirebaseModel();
		firebaseModel.setData(obj2.toString().toString());
		//System.out.println(firebaseModel);
		Firestore firestoreDb = FirestoreClient.getFirestore();
		Firestore secondDB = FirestoreClient.getFirestore(MySecondaryProject.getInstance());
		ApiFuture<WriteResult> collectionsApiFuture;
		ApiFuture<WriteResult> collectionsApiFuture1;
		try {
			collectionsApiFuture = firestoreDb.collection("newscenter_test")
					.document("Markets").set(firebaseModel);
			System.out.println("saving Markets data to firebase"+collectionsApiFuture);
			collectionsApiFuture1 = secondDB.collection("newscenter_test")
					.document("Markets").set(firebaseModel);
			System.out.println("saving News data to firebase"+collectionsApiFuture1);
			return collectionsApiFuture.get().getUpdateTime().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsstatus;
	}

}
