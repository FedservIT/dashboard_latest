package com.fedserv.pulse_dashboard.service;

import com.fedserv.pulse_dashboard.model.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RadioService {
    public String radioLive(Radio_Details radio_details) throws ExecutionException, InterruptedException, IOException {
        Firestore myDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = myDB.collection("live_radio").document("radio");
//        DocumentReference documentReference = firestore.collection("explore_v2").document("Birthday");
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        RadioMangement radioMangement = new RadioMangement();
        Final_Radio_List final_radio_list = new Final_Radio_List();
        RadioList radioList1 = new RadioList();
        List<Radio_Details> array_Radio = new ArrayList();

//        ArrayList<String> list = new ArrayList();
        JSONArray array = new JSONArray();

        if (document.exists()) {
            radioMangement = document.toObject(RadioMangement.class);

            String radioUrl = radioMangement.getJsonUrl();
            System.out.println("URL:::::");
            System.out.println(radioUrl);

            URL url = new URL(radioUrl); // creating a url object
            URLConnection urlConnection = url.openConnection(); // creating a urlconnection object

            List<String> listOfStrings
                    = new ArrayList<String>();
            StringBuilder content = new StringBuilder();

            // wrapping the urlconnection in a bufferedreader
            BufferedReader abc = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            List<String> lines = new ArrayList<String>();

            String line;
            // reading from the urlconnection using the bufferedreader
            while ((line = abc.readLine()) != null) {
                array.add(line);

                content.append(line + "\n");
//
//            String jsonDataString =content.toString() ;
//            JSONObject mainObject = new JSONObject(content.toString());
//            JSONObject valuesObject = new JSONObject();
//            JSONArray list = new JSONArray();
//            valuesObject.put("name", "newValue");
//            valuesObject.put("desc", "newValue");
//            valuesObject.put("longDesc", "newValue");
//            valuesObject.put("streamURL", "newValue");
//            valuesObject.put("imageURL", "newValue");
//                System.out.println(mainObject);
        }
//            list.put(valuesObject);
//            mainObject.accumulate("values", list);

            abc.close();

            final_radio_list.setStation(array);
            System.out.println( final_radio_list.getStation().size());
//            System.out.println( final_radio_list.getStation());
        }
        return (final_radio_list.getStation().toString());

    }
}
//
////        radioList1.setStation();
////        System.out.println(radioList1);
/////        radio_details.setDesc(radioList1.getStation().get(0).getDesc());
////        radio_details.setImageURL(radioList1.getStation().get(0).getImageURL());
////        radio_details.setStreamURL(radioList1.getStation().get(0).getStreamURL());
////        radio_details.setLongDesc(radioList1.getStation().get(0).getLongDesc());
////     System.out.println(radioList1.getStation().size());
//        System.out.println(final_radio_list.getStation().get(2));
////        radioList1.setStation(final_radio_list.getStation().get(0));
////        radioList1.setStation();
//
//        return "com" ;
//
//    }
//
//
//}

//ui
//            radio_details.setStreamURL(radio_details.getStreamURL());
//            radio_details.setImageURL(radio_details.getImageURL());
//            radio_details.setLongDesc(radio_details.getLongDesc());
//            radio_details.setDesc(radio_details.getDesc());
//            radio_details.setName(radio_details.getName());

//            array_Radio.add(radio_details);
//            content.append(array_Radio);

//            list.add(content.toString());
//            System.out.println(list);
//            array_Radio.add(radio_details);
//            radioList1.setStation(array_Radio);

//          System.out.println(radioList1.getStation().size());
// System.out.println(array_Radio.toString());
// System.out.println(list);


//
////       array_Radio.add();
//public String radioLive(Radio_Details radio_details) throws ExecutionException, InterruptedException, IOException {
//    Firestore myDB = FirestoreClient.getFirestore();
//    DocumentReference documentReference = myDB.collection("live_radio").document("radio");
////        DocumentReference documentReference = firestore.collection("explore_v2").document("Birthday");
//    ApiFuture<DocumentSnapshot> future = documentReference.get();
//    DocumentSnapshot document = future.get();
//    RadioMangement radioMangement = new RadioMangement();
//    Final_Radio_List final_radio_list=new Final_Radio_List();
//    RadioList radioList1= new RadioList();
//    List<Radio_Details> array_Radio=new ArrayList();
//
//    ArrayList<String>list= new ArrayList();
//    JSONArray array= new JSONArray();
//
//    if (document.exists()) {
//        radioMangement = document.toObject(RadioMangement.class);
//
//        String radioUrl = radioMangement.getJsonUrl();
//        System.out.println(radioUrl);
//      URL url = new URL(radioUrl); // creating a url object
//       URLConnection urlConnection = url.openConnection(); // creating a urlconnection object
//        System.out.println(urlConnection.getContent());
//
//        List<String> listOfStrings
//                = new ArrayList<String>();
//
//        // wrapping the urlconnection in a bufferedreader
//        BufferedReader abc = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            List<String> lines = new ArrayList<String>();
//
//
//        }
//
//
//
////        radioList1.setStation();
////        System.out.println(radioList1);
/////        radio_details.setDesc(radioList1.getStation().get(0).getDesc());
////        radio_details.setImageURL(radioList1.getStation().get(0).getImageURL());
////        radio_details.setStreamURL(radioList1.getStation().get(0).getStreamURL());
////        radio_details.setLongDesc(radioList1.getStation().get(0).getLongDesc());
////     System.out.println(radioList1.getStation().size());
////    System.out.println(final_radio_list.getStation().get(2));
////        radioList1.setStation(final_radio_list.getStation().get(0));
////        radioList1.setStation();
//
//    return "com" ;

//}





