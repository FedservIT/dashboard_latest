package com.fedserv.pulse_dashboard.service;

//import com.federal.pulseNews.Model.FirebaseModel;
import com.fedserv.pulse_dashboard.model.*;
import com.fedserv.pulse_dashboard.model.FirebaseModel;
import com.gargoylesoftware.htmlunit.WebClient;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class BankingFinancial {
	public Object getOutput() throws Exception {
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
		String docname = modifiedDate.replaceAll("/", "");
		Date date1 = new Date();
		LocalDate localDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		// int day = localDate.getDayOfMonth();
		String newdate;
		if (month < 10) {
			newdate = modifiedDate + "0" + month + year;
		} else {
			newdate = modifiedDate + month + year;
		}
		// System.out.println("date and time" + newdate);
		String searchQuery = "" + newdate + "-todays-banking-financial-news.html";
		String para;
		try (// Instantiate the client
				WebClient client = new WebClient()) {
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);

			// Set up the URL with the search term and send the request
			String searchUrl = "https://iibfadda4u.blogspot.com/?query=" + URLEncoder.encode(searchQuery, "UTF-8");
			// System.out.println("Search url" + searchUrl);
			// HtmlPage page = client.getPage(searchUrl);

			org.jsoup.nodes.Document doc = Jsoup.connect(searchUrl).get();
			System.out.println("All data" + doc);
			doc.select("p").forEach(System.out::println);
			JSONArray jsonarray = new JSONArray();
			// List<String> list = new ArrayList<String>();

			JSONObject jsonresp = new JSONObject();

			for (org.jsoup.nodes.Element x : doc.select("p")) {
				if (!x.text().isEmpty()) {
					JSONArray subjsonarray = new JSONArray();
					jsonresp = new JSONObject();
					para = x.text().toString();
					// jsonresp.put("stringValue", para);
					subjsonarray.put(para);
					jsonarray.put(subjsonarray);
					// System.out.println("Values are" + jsonarray);
				}
			}
			int index = 0;
			int len = jsonarray.length();
			int lastlen = jsonarray.length() - 9;
			// JSONObject obj2 = new JSONObject();
			FirebaseModel firebaseModel = new FirebaseModel();
			for (int k = 0; k < len; k++) {
				if (k == index) {
					jsonarray.remove(k);
				} else {
					for (int j = lastlen; j > lastlen - 9; j--) {
						if (j == lastlen) {
							jsonarray.remove(j);
						}

						firebaseModel = new FirebaseModel();
						firebaseModel.setData(doc.toString());
					}

				}

			}

			// System.out.println(firebaseModel);
			Firestore firestoreDb = FirestoreClient.getFirestore();
			ApiFuture<WriteResult> collectionsApiFuture;
			try {
				collectionsApiFuture = firestoreDb.collection("newsscroll1").document(docname).set(firebaseModel);
				System.out.println("saving newsscroll data to firebase" + collectionsApiFuture);
				return collectionsApiFuture.get().getUpdateTime().toString();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jsonresp.put("status", false);
				jsonresp.put("statusDescription", "Failure");

			}

			return jsonresp;

		}
	}

}
