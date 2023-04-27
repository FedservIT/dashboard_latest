package com.fedserv.pulse_dashboard.service;

import com.fedserv.pulse_dashboard.Constants.collectionConstsants;
import com.fedserv.pulse_dashboard.model.*;
import com.google.api.core.ApiFuture;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class ExploreService {
//    @Value("\\src\\main\\resources\\\ServiceAccount.json")
//    public String filepath;

    Resource serviceAccount = new ClassPathResource("ServiceAccount.json");

    public ExploreData getExplorePageAndImageList() throws ExecutionException, InterruptedException {
        ExploreData exploreData = new ExploreData();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(collectionConstsants.EXPLORE).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        Explore explore = new Explore();
        ExploreDetails exploreDetails = new ExploreDetails();
        List<ExploreDetails> documentTextList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            exploreDetails = new ExploreDetails();
            if (document.exists()) {
                explore = document.toObject(Explore.class);
                exploreDetails.setImageLink(explore.getImgURL());
                exploreDetails.setPageName(explore.getPageName());
                exploreDetails.setText(explore.getText());
                documentTextList.add(exploreDetails);
                exploreData.setExploreDetails(documentTextList);
            } else {
                exploreData.setExploreDetails(documentTextList);
            }
        }

        return exploreData;
    }


    public String addDocumentData(Explore explore) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        Explore explore2 = explore;
        explore2.setIsInternal(explore.getIsInternal().toLowerCase());
        firestore.collection(collectionConstsants.EXPLORE).document(explore2.getPageName()).set(explore2);
        return("completed");

    }

    public Explore getExploreDetailsByPageName(String pageName) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(collectionConstsants.EXPLORE).whereEqualTo("pageName", pageName).get();
        QueryDocumentSnapshot document = future.get().getDocuments().get(0);
        Explore explore = document.toObject(Explore.class);
        return explore;
    }
    /////////////////////////Delete Button in explore page///////////////////////////////
    public String deleteDocumentByPageName(String pageName) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(collectionConstsants.EXPLORE).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        System.out.println(pageName);

        for (QueryDocumentSnapshot document : documents) {
            Explore explore = new Explore();
            explore = document.toObject(Explore.class);
            System.out.println(explore.getPageName());
            if (explore.getPageName().equals(pageName)) {
                System.out.println(explore.getPageName());
                ApiFuture<WriteResult> writeResult = firestore.collection(collectionConstsants.EXPLORE).document(document.getId()).delete();
            }
        }
        return "deleted";
    }



    // Calls the server to securely obtain an unguessable download Url
    public String getDownloadUrl(MultipartFile multipartFile ) throws IOException {
        String filePath1 = "src\\main\\resources\\ServiceAccount.json";
        String path=serviceAccount.getFile().getAbsolutePath();
//        String fileName = "img_assets/" + pageName +".png";
        String fileName = "img_assets/" + multipartFile.getOriginalFilename();
        BlobId blobId = BlobId.of("fbindiafbl.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();

      //  Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(filePath1.toString()));
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(path.toString()));
        StorageOptions storageOptions = StorageOptions.newBuilder()
                .setProjectId("fbindiafbl")
                .setCredentials(credentials).build();

        Storage storage = storageOptions.getService();
        storage.create(blobInfo,multipartFile.getBytes());
//        System.out.println("url   --> " + blob.getMediaLink());
        URL url = storage.signUrl(blobInfo, 5, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature());
        String signedPath = url.toString();
//        System.out.println("signedpath--->" + signedPath);
        return signedPath;
    }

    public String addRetirementData() {
        Firestore firestore = FirestoreClient.getFirestore();
//        Explore explore2 = explore;
        Explore explore = new Explore();

        explore.setDeepLinkURL("");
        explore.setIsInternal("true");
        explore.setMenuType("internal");
        explore.setPageName("retirement");
        explore.setText("Retirements");
        explore.setTileType("internal");
        explore.setImgURL("");
        explore.setIndex("1");
        firestore.collection("explore_v3").document("retirement").set(explore);

        return "com";
    }
}