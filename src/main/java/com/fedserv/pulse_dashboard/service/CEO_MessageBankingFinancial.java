package com.fedserv.pulse_dashboard.service;

//import com.federal.pulseNews.Model.FirebaseModel;
import com.fedserv.pulse_dashboard.model.*;
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
public class CEO_MessageBankingFinancial {
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
		String para1 = "";
		String para2="";
		String para3="";
		String para4="";
		String para5="";
		try (// Instantiate the client
				WebClient client = new WebClient()) {
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);

			// Set up the URL with the search term and send the request
			String searchUrl = "https://iibfadda4u.blogspot.com/?query=" + URLEncoder.encode(searchQuery, "UTF-8");
			// System.out.println("Search url" + searchUrl);
			// HtmlPage page = client.getPage(searchUrl);

			org.jsoup.nodes.Document doc = Jsoup.connect(searchUrl).get();
			//System.out.println("All data" + doc);
			//doc.select("p").forEach(System.out::println);
			JSONObject jsonresp = new JSONObject();
			FirebaseModel firebaseModel = new FirebaseModel();
			JSONObject json = new JSONObject();
			JSONArray subjsonarray = new JSONArray();
			for (org.jsoup.nodes.Element x : doc.select("p")) {
				if (!x.text().isEmpty()) {
					json = new JSONObject();
					para = x.text().toString();
					 //para1 = para1 + "<p>" + para + "</p><br>";
					 subjsonarray.put(para);

				}
			}
			//System.out.println("subjsonarray" + subjsonarray);
			int index=0;
			//int length=subjsonarray.length();
			for(int k=0;k<subjsonarray.length();k++)
			{
				if(k==index)
				{
					para1="<h1>"+subjsonarray.get(k).toString()+"</h1>";
					//System.out.println("<h1>"+para1+"</h1>");
				}
				else if(k==1)
				{
					para2="<h2>"+subjsonarray.get(k).toString()+"</h2>";
					//System.out.println("Para2"+"<h2>"+para2+"");
				}
				else if(k==2)
				{
					para3="<h3>"+subjsonarray.get(k).toString()+"</h3>";
					//System.out.println("Para3"+"<h3>"+para3+"");
				}
				else {
					para4=subjsonarray.get(k).toString();
					//System.out.println("Para4"+"<p>" + para4 + "</p>");
					para5 = para5 + "<p>" + para4 + "</p><br>";
				}
					
			}
		
//			int index = 0;
//			int len = subjsonarray.length();s
//			int lastlen = subjsonarray.length() - 9;
//			for (int k = 0; k < len; k++) {
//				if (k == index) {
//					subjsonarray.remove(k);
//				} else {
//					for (int j = lastlen; j > lastlen - 9; j--) {
//						if (j == lastlen) {
//							subjsonarray.remove(j);
//						}
//						
//						for(int l=0;l<subjsonarray.length();l++)
//						{	
//							 para2=(String) subjsonarray.get(l);
//							 para3 = para3 + para2;
//						}
//					}
//				}
//			}
			
//			System.out.println("para3nd5+++" +para3+para5);
//			System.out.println("para2+++" +para2);
//			System.out.println("para3+++" +para1);
			//String para7=para1+para2+para3+para5;
			String para7=para3+para5;
			String[] data = para7.split("🙏 “All the Best…Have a Good Day");
			
			for(int i=0;i<data.length;i++)
			{
				System.out.println("data+++" + data[i]);
			}			
			String para6="<html><body>" + data[0]+"<p>🙏 “All the Best…Have a Good Day \"🌻💐👩‍👩‍👧‍👦🙏</p><br>"  +"</body></html>";
			firebaseModel = new FirebaseModel();
			firebaseModel.setData(para6);
			Firestore firestoreDb = FirestoreClient.getFirestore();
			ApiFuture<WriteResult> collectionsApiFuture;
			//System.out.println("Final para"+para6);
			try {
				collectionsApiFuture = firestoreDb.collection("newsscroll1").document(docname).set(firebaseModel);
				System.out.println("Saving CEOMessage data to firebase" + collectionsApiFuture);
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
