package com.fedserv.pulse_dashboard.service;


import com.fedserv.pulse_dashboard.firebvaseinitilization.FirebaseInitilization;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class MySecondaryProject {
	private static FirebaseApp INSTANCE = null;

	public static FirebaseApp getInstance() throws IOException {
		if (INSTANCE == null) {
			INSTANCE = getSecondProject();
		}
		return INSTANCE;
	}

	private  static FirebaseApp getSecondProject() throws IOException {
		 try {
//            InputStream serviceAccount = new ClassPathResource("ServiceAccount.json").getInputStream(); //
//                  //   new FileInputStream("src/main/resources/ServiceAccount.json");
//
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setDatabaseUrl("https://fbindiafbl-default-rtdb.asia-southeast1.firebasedatabase.app")
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//			 FirebaseInitilization firebaseInitilization = new FirebaseInitilization();
//			 firebaseInitilization.initialize();

		return FirebaseApp.getInstance();

        }catch (Exception e){
            e.printStackTrace();
        }
		return INSTANCE;
	}

}