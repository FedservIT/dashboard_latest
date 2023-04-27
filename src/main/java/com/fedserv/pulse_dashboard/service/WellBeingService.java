package com.fedserv.pulse_dashboard.service;

import com.fedserv.pulse_dashboard.Constants.collectionConstsants;
import com.fedserv.pulse_dashboard.model.WellBeing;
import com.fedserv.pulse_dashboard.model.WellBeingList;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WellBeingService {

    public WellBeingList addWellBeing(WellBeingList wellBeingList) {
        Firestore firestore = FirestoreClient.getFirestore();
        WellBeing wellBeing = new WellBeing();
//        WellBeingList wellBeingList=new WellBeingList();
        List<WellBeing> wellBeingList1 = new ArrayList<>();

        wellBeingList.setIndex(wellBeingList.getIndex());
        wellBeingList.setType(wellBeingList.getType());

        DocumentReference addedDocRef = firestore.collection("well_test").document(String.valueOf(wellBeingList.getIndex()));
        for (int i = 0; i < wellBeingList.getData().size(); i++) {
            wellBeing.setIndex(wellBeingList.getData().get(i).getIndex());
            wellBeing.setPlace_holder(wellBeingList.getData().get(i).getPlace_holder());
            wellBeing.setTitle(wellBeingList.getData().get(i).getTitle());
            wellBeing.setUrl(wellBeingList.getData().get(i).getUrl());
            wellBeing.setWeb_url(wellBeingList.getData().get(i).getWeb_url());
            wellBeingList.getData().set(i, wellBeing);
            ApiFuture<WriteResult> collectionApiFuture1 = addedDocRef.set(wellBeingList);
        }

        return wellBeingList;

    }
}


