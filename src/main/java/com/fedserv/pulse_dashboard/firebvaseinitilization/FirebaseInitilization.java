package com.fedserv.pulse_dashboard.firebvaseinitilization;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FirebaseInitilization {
    @Value("${filepath}")
    public String filepath;
    @PostConstruct
    public  void  initialize(){

        try {
//            String fileLocation=filepath;

//            FileInputStream serviceAccount =
//                    new FileInputStream(filepath);
            Resource serviceAccount = new ClassPathResource("ServiceAccount.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                    .setDatabaseUrl("https://fbindiafbl-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .build();

            FirebaseApp.initializeApp(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
