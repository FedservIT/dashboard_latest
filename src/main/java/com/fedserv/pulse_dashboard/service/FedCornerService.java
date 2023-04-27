package com.fedserv.pulse_dashboard.service;

import com.fedserv.pulse_dashboard.Constants.collectionConstsants;
import com.fedserv.pulse_dashboard.model.Federal;
import com.fedserv.pulse_dashboard.model.FederalData;
import com.fedserv.pulse_dashboard.model.FederalDetails;
import com.google.api.client.json.Json;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FedCornerService {

    public FederalData getFederalExplorePageAndImageList() throws ExecutionException, InterruptedException {
        FederalData federalData = new FederalData();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(collectionConstsants.FEDCORNER).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        FederalDetails federalDetails = new FederalDetails();
        List<FederalDetails> documentTextList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            federalDetails = new FederalDetails();
            if (document.exists()) {
                federalDetails.setImageLink(document.get("imgUrl").toString());
                federalDetails.setPageType(document.get("pageType").toString());
                federalDetails.setTitle(document.get("title").toString());
                documentTextList.add(federalDetails);
                federalData.setFederalDetails(documentTextList);
            } else {
                federalData.setFederalDetails(documentTextList);
            }
        }

        return federalData;
    }


    public Federal getFedExploreDetailsByPageName(String pageName) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(collectionConstsants.FEDCORNER).get();
        List<Federal> federalList = new ArrayList<>();
        Federal federal = new Federal();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            federalList.add(document.toObject(Federal.class));
        }
        for (int i = 0; i < federalList.size(); i++) {

            if (pageName.equals(federalList.get(i).getPageType())) {
                federal.setDeepLinkURL(federalList.get(i).getDeepLinkURL());
                federal.setImgURL(federalList.get(i).getImgURL());
                federal.setIsInternal(federalList.get(i).getIsInternal());
                federal.setPageType(federalList.get(i).getPageType());
                federal.setPdfURL(federalList.get(i).getPdfURL());
                federal.setTitle(federalList.get(i).getTitle());
                federal.setVideoURL(federalList.get(i).getVideoURL());
            }
        }
        return federal;
    }

    /////////////////////////Delete Button in federalexplore page///////////////////////////////
    public String deleteFedDocumentByPageName(String pageType) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(collectionConstsants.FEDCORNER).get();
        Federal federal = new Federal();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            federal = new Federal();
            federal = document.toObject(Federal.class);
            if (federal.getPageType().equals(pageType)) {
                ApiFuture<WriteResult> writeResult = firestore.collection(collectionConstsants.FEDCORNER).document(document.getId()).delete();
            }
        }
        return "deleted";
    }



}
