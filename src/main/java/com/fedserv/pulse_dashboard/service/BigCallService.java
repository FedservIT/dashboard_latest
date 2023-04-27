package com.fedserv.pulse_dashboard.service;

import com.fedserv.pulse_dashboard.Constants.collectionConstsants;
//import com.fedserv.pulse_dashboard.firebvaseinitilization.FirebaseInitilization;
import com.fedserv.pulse_dashboard.model.BigCall;
import com.fedserv.pulse_dashboard.model.BigCallStatus;
import com.fedserv.pulse_dashboard.model.Contest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class BigCallService {
    public BigCallStatus getBigcallUrl() throws ExecutionException, InterruptedException {
        Firestore myDB = FirestoreClient.getFirestore();

//        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();

        DocumentReference documentReference = myDB.collection(collectionConstsants.BIGCALL).document("status");
        //        DocumentReference documentReference = firestore.collection("explore_v2").document("Birthday");
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        BigCallStatus bigCallStatus = null;

        if (document.exists()) {
            bigCallStatus = document.toObject(BigCallStatus.class);
            return bigCallStatus;
        } else {
            return new BigCallStatus();
        }

    }

    public BigCall addBigcallUrl(BigCall bigCall) {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(collectionConstsants.BIGCALL).document("broad_cast_url").set(bigCall);
        return bigCall;
    }

    public BigCallStatus addBigcallstatus(BigCallStatus bigCallStatus) {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(collectionConstsants.BIGCALL).document("status").set(bigCallStatus);
        return bigCallStatus;
    }
}

