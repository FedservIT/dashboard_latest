package com.fedserv.pulse_dashboard.service;

import com.fedserv.pulse_dashboard.model.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CeoService {
    public CeoSpeak getCeoSpeak() throws ExecutionException, InterruptedException, ParseException {
        Firestore myDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = myDB.collection("ceospeak_v2").document("CEO_MSG");
//        DocumentReference documentReference = firestore.collection("explore_v2").document("Birthday");
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        CeoSpeak ceoSpeak = null;
        if (document.exists()) {
            ceoSpeak = document.toObject(CeoSpeak.class);
//            System.out.println(ceoSpeak);
            String outputText = Jsoup.clean(ceoSpeak.getMsg(), new Whitelist());
//            System.out.println(outputText);
            ceoSpeak.setMsg(outputText);
            String str = ceoSpeak.getId();
            System.out.println(str);


            String[] arrOfStr = str.split("_", 3);

            ceoSpeak.setId(arrOfStr[2]);
            String time = arrOfStr[2];
            SimpleDateFormat format1 = new SimpleDateFormat("ddMMyyyy");
            SimpleDateFormat format2 = new SimpleDateFormat("dd MMM yyyy");
            Date date = format1.parse(time);
            ceoSpeak.setId(format2.format(date));

            return ceoSpeak;
        } else
            return new CeoSpeak();
    }


    public CeoSpeak addCeoSpeak(CeoSpeak ceoSpeak) {
        Firestore firestore = FirestoreClient.getFirestore();
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        String str = formatter.format(date);

        System.out.println(str);
        String id = "ceo_speaks_" + str;
        ceoSpeak.setId(id);

        System.out.println("time" + timeStampMillis);
        ceoSpeak.setTimestamp(timeStampMillis / 1000);
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection("test_test").document("CEO_MSG").set(ceoSpeak);
       collectionApiFuture = firestore.collection("testing").document(str).set(ceoSpeak);

        return ceoSpeak;

    }

    //
    public String ceoSpeakLikes() {


        return "completed";
    }


    public int ceoSpeakViews(String speak_id) throws ExecutionException, InterruptedException {
        Firestore myDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = myDB.collection("ceo_speak_views").document(speak_id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
//        List<CeoSpeakLike> viewList= new ArrayList<>();
        CeoSpeakLike ceoSpeakLike = new CeoSpeakLike();
        if (document.exists()) {
            ceoSpeakLike = document.toObject(CeoSpeakLike.class);

            System.out.println(ceoSpeakLike.getData().size());
            return ceoSpeakLike.getData().size();
//            return ceoSpeakLike;

        } else {

            return 0;

        }


    }

    public CeoSpeakList ceoSpeakHistory() throws ExecutionException, InterruptedException {

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("ceospeakhistory_v2").get();
           CeoSpeakHis ceoSpeakHis= new CeoSpeakHis();
           List<CeoSpeakHis> ceoSpeakHisList = new ArrayList<>();
           CeoSpeakList ceoSpeakList= new CeoSpeakList();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            ceoSpeakHis= new CeoSpeakHis();
            String ceoDocId="";
            ceoDocId = document.getId();
            ceoSpeakHis.setCeo_id(ceoDocId);

            DocumentReference documentReference = firestore.collection("ceospeakhistory_v2").document(ceoDocId);
            ApiFuture<DocumentSnapshot> future1 = documentReference.get();
            DocumentSnapshot document1 = future1.get();
            CeoSpeak ceospeak = new CeoSpeak();
            if (document1.exists()) {
                ceospeak = document1.toObject(CeoSpeak.class);
                String outputText = Jsoup.clean(ceospeak.getMsg(), new Whitelist());
               ceoSpeakHis.setMsg(outputText);
            }
            ceoSpeakHisList.add(ceoSpeakHis);
            System.out.println(ceoSpeakHisList.toString());
        }

        ceoSpeakList.setCeoSpeakHisList(ceoSpeakHisList);
  return  ceoSpeakList;
    }

}