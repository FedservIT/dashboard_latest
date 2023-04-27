package com.fedserv.pulse_dashboard.service;

import com.fedserv.pulse_dashboard.Constants.collectionConstsants;
import com.fedserv.pulse_dashboard.model.*;
import com.google.api.core.ApiFuture;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.*;
import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.FirestoreClient;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Workbook;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
@Service
public class BannerService {


    //    @Value("D:\\17-08-2022\\src\\main\\resources\\static\\ServiceAccount.json")
//    public String filepath;


    Resource serviceAccount = new ClassPathResource("ServiceAccount.json");
    public String bannerUpload(MultipartFile multipartFile, String date) throws IOException {
        String path=serviceAccount.getFile().getAbsolutePath();
        String fileName = "img_assets/" + date + ".png";
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

    List<String> fameArray = new ArrayList<>();
    List<String> empArray = new ArrayList<>();

    public List<String> installDta() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("employee_details_v2").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<String> docArray = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            docArray.add(document.getId());
            ;
        }
        System.out.println("check" + docArray);
        empArray = docArray;
        System.out.println("empArray" + empArray);
        System.out.println("size" + docArray.size());
        return docArray;
    }

    public List<String> fameDbCount() throws ExecutionException, InterruptedException, IOException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("FAME_DB").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<String> docArray1 = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            docArray1.add(document.getId());
            ;
        }
        System.out.println("INSTALLED......" + docArray1);
        fameArray = docArray1;

//....................................................

        try
        {
            Workbook wb = new HSSFWorkbook();
            OutputStream fileOut1 = new FileOutputStream("D:\\Installed.xls");
//        System.out.println("Excel File has been created successfully.");
            wb.write(fileOut1);
            String filename = "D:\\Installed.xls";
//creating an instance of HSSFWorkbook class
            HSSFWorkbook workbook = new HSSFWorkbook();
//invoking creatSheet() method and passing the name of the sheet to be created
            HSSFSheet sheet = workbook.createSheet("Installed");
//creating the 0th row using the createRow() method
            HSSFRow rowhead = sheet.createRow((short)0);
//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method
            rowhead.createCell(0).setCellValue("PFNUM");
         for(int i=0;i<docArray1.size();i++) {
//creating the 1st row
    HSSFRow row = sheet.createRow((short) i);
//inserting data in the first row
    row.createCell(0).setCellValue(docArray1.get(i));
}
    FileOutputStream fileOut = new FileOutputStream(filename);

    workbook.write(fileOut);

//closing the Stream
    fileOut.close();

//closing the workbook
    workbook.close();

//prints the message on the console
            System.out.println("Excel file has been generated successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return docArray1;
    }

    public String pfNmCompare() throws ExecutionException, InterruptedException, IOException {
        empArray = this.installDta();
        fameArray = this.fameDbCount();
        List<String> empArray1 = new ArrayList<>();
        List<String> empArray2 = new ArrayList<>();
        List<String> fameArray1 = new ArrayList<>();
        List<String> fameArray2 = new ArrayList<>();
        System.out.println("emp" + empArray);
        System.out.println("fame" + fameArray);

        fameArray1 = fameArray;
        empArray1 = empArray;

        //UNINSTALLED USERS>>>>>>>>>>>>
        empArray1.removeAll(fameArray1);
        System.out.println("UNINSTALLED USERS" + empArray1);

        try
        {
            Workbook wb = new HSSFWorkbook();
            OutputStream fileOut1 = new FileOutputStream("D:\\UnInstalled.xls");
//        System.out.println("Excel File has been created successfully.");
            wb.write(fileOut1);
            String filename = "D:\\UnInstalled.xls";
//creating an instance of HSSFWorkbook class
            HSSFWorkbook workbook = new HSSFWorkbook();
//invoking creatSheet() method and passing the name of the sheet to be created
            HSSFSheet sheet = workbook.createSheet("UnInstalled");
//creating the 0th row using the createRow() method
            HSSFRow rowhead = sheet.createRow((short)0);
//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method
            rowhead.createCell(0).setCellValue("PFNUM");
            for(int i=0;i<empArray1.size();i++) {
//creating the 1st row
                HSSFRow row = sheet.createRow((short) i);
//inserting data in the first row
                row.createCell(0).setCellValue(empArray1.get(i));
            }
            FileOutputStream fileOut = new FileOutputStream(filename);

            workbook.write(fileOut);

//closing the Stream
            fileOut.close();

//closing the workbook
            workbook.close();

//prints the message on the console
            System.out.println("Excel file has been generated successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return "Sucess";
    }

    public String iphoneDetails() throws ExecutionException, InterruptedException, ParseException {
        // asynchronously retrieve all documents
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("FAME_DB").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        int i = 0;
        for (QueryDocumentSnapshot document : documents) {
            String docId = document.getId();
            DocumentReference documentReference = firestore.collection("FAME_DB").document(docId);
            ApiFuture<DocumentSnapshot> future1 = documentReference.get();
            DocumentSnapshot document1 = future1.get();
            IphoneUsers iphoneUsers = new IphoneUsers();

            int j = 0;
            if (document.exists()) {
                iphoneUsers = document.toObject(IphoneUsers.class);
                String users = iphoneUsers.getDeviceModel();
                List <Integer> count = new ArrayList<>();
                if (users.startsWith("iPhone")) {

//                    System.out.println(iphoneUsers.getEmpId());
//                    count.add(1);
                   i = i +1;


                }

            }

        }
        System.out.println(i);
        return "com";


    }
}

