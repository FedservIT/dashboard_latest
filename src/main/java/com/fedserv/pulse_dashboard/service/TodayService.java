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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class TodayService {

    Resource serviceAccount = new ClassPathResource("ServiceAccount.json");
//    @Value("D:\\17-08-2022\\src\\main\\resources\\static\\ServiceAccount.json")
//    public String filepath;
///// add todayForYou

//    public TodayForYou addTodayInfo(TodayForYou todayForYou) {
//
//        Firestore firestore = FirestoreClient.getFirestore();
//        ApiFuture<WriteResult> collectionApiFuture = firestore.collection("test_test").document(todayForYou.getDate()).set(todayForYou);
//
//        return todayForYou;
//
//    }

//////////////////////////Image Upload

    public String upload(MultipartFile multipartFile) throws IOException {
        String path=serviceAccount.getFile().getAbsolutePath();
        String fileName = "img_assets/" + multipartFile.getOriginalFilename();
        BlobId blobId = BlobId.of("fbindiafbl.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream((path.toString())));
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

    /////////////////////////////////////////////Fetch joke and quotes Info


    public TodayList getJokesInfo() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(collectionConstsants.JOKES).get();
// future.get() blocks on response
        Today today= new Today();
        TodayList todayList= new TodayList();
        List<Today> todayListDetails = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        Object obj=new Object();
        for (QueryDocumentSnapshot document : documents) {
            today= new Today();
            String docId=document.getId();
//            System.out.println(docId+ " => " + document.toObject(TodayForYou.class));
            today.setPublishDate(docId);
            todayListDetails.add(today);

        DocumentReference documentReference = firestore.collection(collectionConstsants.JOKES).document(docId);
            ApiFuture<DocumentSnapshot> future1 = documentReference.get();
            DocumentSnapshot document1 = future1.get();
            TodayForYou todayForYou=new TodayForYou();
            if (document.exists()) {
                todayForYou = document.toObject(TodayForYou.class);
                today.setPublishData(todayForYou.getData1());
            }
        }
//        System.out.print(todayListDetails);
        todayList.setTodayForYouList(todayListDetails);
        return todayList;
    }


    //////////////////add smiles
    public Smiles addSmiles(Smiles smiles) {

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(collectionConstsants.SMILES).document("doc2").set(smiles);
        return smiles;

    }
    ////////////////// fetch trivia and did you know

    public TodayList getTrivia() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(collectionConstsants.TRIVIA).get();
        Today today= new Today();
        TodayList todayList= new TodayList();
        List<Today> todayListDetails = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        Object obj=new Object();
        for (QueryDocumentSnapshot document : documents) {
            today= new Today();
            String docId=document.getId();
//            System.out.println(docId+ " => " + document.toObject(TodayForYou.class));
            today.setPublishDate(docId);
            todayListDetails.add(today);

            DocumentReference documentReference = firestore.collection(collectionConstsants.TRIVIA).document(docId);
            ApiFuture<DocumentSnapshot> future1 = documentReference.get();
            DocumentSnapshot document1 = future1.get();
            TodayForYou todayForYou=new TodayForYou();
            if (document.exists()) {
                todayForYou = document.toObject(TodayForYou.class);
                today.setPublishData(todayForYou.getTitle());
            }
        }
//        System.out.print(todayListDetails);
        todayList.setTodayForYouList(todayListDetails);
        return todayList;
    }
    ////////////////// fetch  did you know

    public TodayList getDidYouKnow() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(collectionConstsants.DIDYOUKNOW).get();
        Today today= new Today();
        TodayList todayList= new TodayList();
        List<Today> todayListDetails = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        Object obj=new Object();
        for (QueryDocumentSnapshot document : documents) {
            today= new Today();
            String docId=document.getId();
//            System.out.println(docId+ " => " + document.toObject(TodayForYou.class));
            today.setPublishDate(docId);
            todayListDetails.add(today);

            DocumentReference documentReference = firestore.collection(collectionConstsants.DIDYOUKNOW).document(docId);
            ApiFuture<DocumentSnapshot> future1 = documentReference.get();
            DocumentSnapshot document1 = future1.get();
            TodayForYou todayForYou=new TodayForYou();
            if (document.exists()) {
                todayForYou = document.toObject(TodayForYou.class);
                today.setPublishData(todayForYou.getTitle());
            }
        }
//        System.out.print(todayListDetails);
        todayList.setTodayForYouList(todayListDetails);
        return todayList;

    }


    public String addTrivia(String date,String ColletionName,TodayForYou todayForYou) throws ParseException {
//        public String addTrivia(TodayForYou todayForYou) {
//         TodayForYou todayForYou= new TodayForYou();
        String time = date;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("ddMMyyyy");
        Date date1 = format1.parse(time);
       String currentDate = format2.format(date1);

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(ColletionName).document(currentDate).set(todayForYou);
        return "Completed";

    }


    public ExcelModelList update(MultipartFile multipartFile,String collName) {

        try {

//            File file = new File(multipartFile.getInputStream());   //creating a new file instance
//            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            XSSFWorkbook wb = new XSSFWorkbook(multipartFile.getInputStream());
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file

//            ReadExcelList readExcelList = new ReadExcelList();
            ExcelModelList excelModelList=new ExcelModelList();
            List<ReadExcel> listExcel = new ArrayList<>();
            ExcelModel excelModel = new ExcelModel();
            List<ExcelModel>excelList= new ArrayList<>();
            int rowNum = wb.getSheetAt(0).getLastRowNum();

            int colNum = wb.getSheetAt(0).getRow(0).getLastCellNum();
            for (int i = 1; i < rowNum + 1; i++) {

//                System.out.println("date -- >  "+wb.getSheetAt(0).getRow(i).getCell(0));

                ReadExcel readExcel = new ReadExcel(wb.getSheetAt(0).getRow(i).getCell(0) + "", wb.getSheetAt(0).getRow(i).getCell(1) + "", wb.getSheetAt(0).getRow(i).getCell(2) + "");
              String date= readExcel.getDate();
                SimpleDateFormat format1 = new SimpleDateFormat("ddMMyyyy");
//             newDate = date;
                excelModel.setTitle(readExcel.getTitle());
                excelModel.setData1(readExcel.getData1());
                excelList.add(excelModel);
//                String reportDate = format1.format(wb.getSheetAt(0).getRow(i).getCell(0).getCellFormula());
//                listExcel.add(readExcel);
//                System.out.println("DATE -  >  "+reportDate);

                FirestoreClient.getFirestore().collection(collName).document(date).set(excelModel);

            }
//            readExcelList.setReadExcelList(listExcel);
            excelModelList.setExcelList(excelList);

            //System.out.println("----CONTENT-----" + listExcel);
            return excelModelList;


        } catch (Exception e) {
            e.printStackTrace();

        }

        return new ExcelModelList();
    }

    }












