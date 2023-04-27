package com.fedserv.pulse_dashboard.service;

import com.fedserv.pulse_dashboard.Constants.collectionConstsants;
import com.fedserv.pulse_dashboard.model.Contest;
import com.fedserv.pulse_dashboard.model.FederalCorner;
import com.google.api.core.ApiFuture;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class ContestService {
//    @Value("D:\\17-08-2022\\src\\main\\resources\\static\\ServiceAccount.json")
//
//    public String filepath;

    Resource serviceAccount = new ClassPathResource("ServiceAccount.json");
    public String addContest(Contest contest) {
        Firestore firestore = FirestoreClient.getFirestore();
//        ApiFuture<WriteResult> collectionApiFuture = firestore.collection("testing").document().set(contest);
        DocumentReference ref = firestore.collection(collectionConstsants.CONTEST).document();
        ApiFuture<WriteResult> collectionApiFuture=ref.set(contest);

        return("completed");
    }

    public Contest getContest(String docId) throws ExecutionException, InterruptedException {
        Firestore myDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = myDB.collection(collectionConstsants.CONTEST).document(docId);
        //        DocumentReference documentReference = firestore.collection("explore_v2").document("Birthday");
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Contest contest = null;
        if (document.exists()) {
            contest = document.toObject(Contest.class);
            return contest;

        } else

            return new Contest();
    }
    public String uploaData(MultipartFile multipartFile) throws IOException {
        String path=serviceAccount.getFile().getAbsolutePath();
        String fileName = "img_assets/" + multipartFile.getOriginalFilename();
        String bucketName = "fbindiafbl.appspot.com";

        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(path.toString()));
        StorageOptions storageOptions = StorageOptions.newBuilder()
                .setProjectId("fbindiafbl")
                .setCredentials(credentials).build();
        Storage storage = storageOptions.getService();
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
        Blob blob = storage.create(blobInfo, multipartFile.getBytes());
        System.out.println("url   --> " + blob.getMediaLink());
        URL url1 = storage.signUrl(blobInfo, 5, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature());
        String signedPath = url1.toString();
        System.out.println("signedpath--->" + signedPath);

        return signedPath;
    }

}

