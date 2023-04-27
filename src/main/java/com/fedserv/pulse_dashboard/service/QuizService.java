package com.fedserv.pulse_dashboard.service;

import com.fedserv.pulse_dashboard.Constants.collectionConstsants;
import com.fedserv.pulse_dashboard.model.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class QuizService {

////////////////////////////// Fetch question and Answer

    public QuizData getQuizDetails(String quiz_id) throws ExecutionException, InterruptedException {
        Firestore myDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = myDB.collection(collectionConstsants.QUIZ_HISTORY).document(quiz_id);
//        DocumentReference documentReference = firestore.collection("explore_v2").document("Birthday");
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        QuizData quizData = new QuizData();
        if (document.exists()) {
            quizData = document.toObject(QuizData.class);
            return quizData;
        } else
            return new QuizData();
    }

    //////////////////////////////Add Quiz

    public QuizData addQuizData(QuizData quizData) throws ParseException {
        Firestore firestore = FirestoreClient.getFirestore();
        Quiz quiz = new Quiz();
//        LocalDateTime ldt = LocalDateTime.now();
//        String publishDate = (DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH).format(ldt));
////        quizData.setPublished_date(publishDate);

        String time = quizData.getPublished_date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("ddMMyyyy");
        Date date1 = format1.parse(time);
        String currentDate = format2.format(date1);
        quizData.setPublished_date(currentDate);
        DocumentReference ref = firestore.collection(collectionConstsants.QUIZ_HISTORY).document();
        String myId = ref.getId();
//        System.out.println(myId);
        quizData.setQuiz_id(myId);
        quizData.setIsActive("true");

        for (int i = 0; i < quizData.getData().size(); i++) {
            quiz = new Quiz();
//            System.out.println(quizData.getData().size());
//            quiz.setQuizId("q" + (i + 1));

            quiz.setQuizId(quizData.getData().get(i).getQuizId());
            quiz.setAnswer(quizData.getData().get(i).getAnswer());
            quiz.setOption1(quizData.getData().get(i).getOption1());
            quiz.setOption2(quizData.getData().get(i).getOption2());
            quiz.setOption3(quizData.getData().get(i).getOption3());
//            quiz.setOption4(quizData.getData().get(i).getOption4());
            quiz.setQuestion(quizData.getData().get(i).getQuestion());

            quizData.getData().set(i, quiz);

            ApiFuture<WriteResult> collectionApiFuture1 = ref.set(quizData);
//            collectionApiFuture1 = firestore.collection("quizmasterHistory_v2").document(myId).set(quizData);
        }

        return quizData;
    }


    //////////////////// Add Quiz Info


    public QuizInfo addQuizInfo(QuizInfo quizInfo) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(collectionConstsants.QUIZINFO).document("info").set(quizInfo);

        return quizInfo;

    }

    ////////////////Document_id and Publish date

    public QuizList getQuizInfo() throws ExecutionException, InterruptedException, ParseException {
        // asynchronously retrieve all documents
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(collectionConstsants.QUIZ_HISTORY).get();
// future.get() blocks on response
        QuizDetails quizDetails = new QuizDetails();
        QuizList quizList = new QuizList();
        List<QuizDetails> quizListDetails = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        Object obj = new Object();
        for (QueryDocumentSnapshot document : documents) {
            quizDetails = new QuizDetails();
            String docId = document.getId();
//            System.out.println(docId + " => " + document.toObject(QuizData.class));
            quizDetails.setDoc_Id(docId);
            quizListDetails.add(quizDetails);
            DocumentReference documentReference = firestore.collection(collectionConstsants.QUIZ_HISTORY).document(docId);
            ApiFuture<DocumentSnapshot> future1 = documentReference.get();
            DocumentSnapshot document1 = future1.get();
            QuizData quizData = new QuizData();
            if (document.exists()) {
                quizData = document.toObject(QuizData.class);
                String time1 = quizData.getPublished_date();
                SimpleDateFormat format1 = new SimpleDateFormat("ddMMyyyy");
                SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
                Date date1 = format1.parse(time1);
                String currentDate = format2.format(date1);
                quizDetails.setPublishDate(currentDate);
                quizDetails.setIsActive(quizData.getIsActive());
            }
        }
//        System.out.print(quizListDetails);
        quizList.setQuizInfoList(quizListDetails);
        return quizList;
    }

    ///////////////////Get Quiz info

    public QuizInfo getQuizContent() throws ExecutionException, InterruptedException {
        Firestore myDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = myDB.collection(collectionConstsants.QUIZINFO).document("info");
//        DocumentReference documentReference = firestore.collection("explore_v2").document("Birthday");
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        QuizInfo quizInfo = null;
        if (document.exists()) {
            quizInfo = document.toObject(QuizInfo.class);
            return quizInfo;
        } else
            return new QuizInfo();
    }


    public QuizWinner getQuizWinnerFirst() throws ExecutionException, InterruptedException {
        Firestore myDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = myDB.collection("quizWinnerTest").document("first");
//        DocumentReference documentReference = firestore.collection("explore_v2").document("Birthday");
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        QuizWinner quizWinner = new QuizWinner();
        if (document.exists()) {
            quizWinner = document.toObject(QuizWinner.class);
            return quizWinner;
        } else
            return new QuizWinner();
    }

    public QuizWinner getQuizWinnerSecond() throws ExecutionException, InterruptedException {
        Firestore myDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = myDB.collection("quizWinnerTest").document("second");
//        DocumentReference documentReference = firestore.collection("explore_v2").document("Birthday");
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        QuizWinner quizWinner = new QuizWinner();
        if (document.exists()) {
            quizWinner = document.toObject(QuizWinner.class);
            return quizWinner;
        } else
            return new QuizWinner();
    }

    public QuizWinner getQuizWinnerThird() throws ExecutionException, InterruptedException {
        Firestore myDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = myDB.collection("quizWinnerTest").document("third");

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        QuizWinner quizWinner = new QuizWinner();
        if (document.exists()) {
            quizWinner = document.toObject(QuizWinner.class);
            return quizWinner;
        } else
            return new QuizWinner();
    }
//QUIZ Participants Listing.........................................

    public QuizScoreArray getQuizMasterAnswer(String quiz_id) throws ExecutionException, InterruptedException {
        Firestore myDB = FirestoreClient.getFirestore();
//        System.out.println("document : " + quiz_id);

        DocumentReference documentReference = myDB.collection(collectionConstsants.QUIZ_ANS).document(quiz_id.replace("\n", ""));
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        QuizScoreArray qArray = new QuizScoreArray();
        QuizMasterAnswer quizMasterAnswer = null;
        QuizScore quizScoreData = new QuizScore();
        List<QuizScore> listQuizScore = new ArrayList<>();

//        System.out.println("document : " + document.getId());
//        System.out.println("document : " + document.getData());
        if (document.exists()) {
            quizMasterAnswer = document.toObject(QuizMasterAnswer.class);
            int length = quizMasterAnswer.getAttemptedUsers().size();
            for (int i = 0; i < length; i++) {
//                System.out.println("getScore : " + quizMasterAnswer.getAttemptedUsers().get(i).getEmpID());
                quizScoreData = new QuizScore();
                quizScoreData.setEmpID(quizMasterAnswer.getAttemptedUsers().get(i).getEmpID());
                quizScoreData.setScore(quizMasterAnswer.getAttemptedUsers().get(i).getScore());
                listQuizScore.add(quizScoreData);
            }
            qArray.setQuizScore(listQuizScore);
            System.out.println("QUIZ RESULT::::" + listQuizScore.size());
            //////////////////////////////////////// excel

            try {
                Workbook wb = new HSSFWorkbook();
                OutputStream fileOut1 = new FileOutputStream("D:\\QuizMaster.xls");
//        System.out.println("Excel File has been created successfully.");
                wb.write(fileOut1);
                String filename = "D:\\QuizMaster.xls";
//creating an instance of HSSFWorkbook class
                HSSFWorkbook workbook = new HSSFWorkbook();
//invoking creatSheet() method and passing the name of the sheet to be created
                HSSFSheet sheet = workbook.createSheet("QuizMaster");
//creating the 0th row using the createRow() method
                HSSFRow rowhead = sheet.createRow((short) 0);
//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method
                rowhead.createCell(0).setCellValue("PFNUM");
                rowhead.createCell(1).setCellValue("SCORE");
                for (int i = 0; i < listQuizScore.size(); i++) {
//creating the 1st row
                    HSSFRow row = sheet.createRow((short) i);
//inserting data in the first row
                    row.createCell(0).setCellValue(listQuizScore.get(i).getEmpID());
                    row.createCell(1).setCellValue(listQuizScore.get(i).getScore());
                }
                FileOutputStream fileOut = new FileOutputStream(filename);

                workbook.write(fileOut);

//closing the Stream
                fileOut.close();

//closing the workbook
                workbook.close();

//prints the message on the console
                System.out.println("Excel file has been generated successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }


            return qArray;
        } else
            return new QuizScoreArray();
    }

    ///////////////////////////////////// Get EMPID

    public String getQuizMasterNames(String empID) throws ExecutionException, InterruptedException {
        Firestore myDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = myDB.collection("FAME_DB").document(empID);
//        DocumentReference documentReference = firestore.collection("explore_v2").document("Birthday");
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        QuizParticipantsNames names = null;
        if (document.exists()) {
            names = document.toObject(QuizParticipantsNames.class);
            return names.getEmpName();
        } else
            return names.getEmpName();

    }

    //////////////////////////////////Get Image
    public String getQuizMasterImages(String empID) throws ExecutionException, InterruptedException {
        Firestore myDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = myDB.collection("profileimg").document(empID);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        QuizMasterImages images = null;
        if (document.exists()) {
            images = document.toObject(QuizMasterImages.class);
            return images.getPhoto();
        } else
            return images.getPhoto();
    }
    ///////////////////////////////////////

    public QuizWinner addQuizFirst(QuizWinner quizWinner) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection("quizWinnerTest").document("first").set(quizWinner);
        return quizWinner;

    }

    public QuizWinner addQuizSecond(QuizWinner quizWinner) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection("quizWinnerTest").document("second").set(quizWinner);
        return quizWinner;

    }

    public QuizWinner addQuizThird(QuizWinner quizWinner) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection("quizWinnerTest").document("third").set(quizWinner);
        return quizWinner;

    }


//       @Scheduled(cron = "0 0/10 * * * ?")
// @Scheduled(cron = "*/5 * * * * * ")
    public void quizTimer() throws ExecutionException, InterruptedException, ParseException {
        this.getQuizInfo();

        QuizList qList = new QuizList();
        LocalDateTime ldt = LocalDateTime.now();
        String publishDate = (DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH).format(ldt));
//        System.out.println("publishDate"+publishDate);
        SimpleDateFormat format1 = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = format1.parse(publishDate);
        String currentDate = format2.format(date1);
//       System.out.println(currentDate);
        qList = this.getQuizInfo();
        QuizData quizdata2 = new QuizData();
        for (int i = 0; i < qList.getQuizInfoList().size(); i++) {
            qList.getQuizInfoList().get(i).getPublishDate();


            if (currentDate.equals(qList.getQuizInfoList().get(i).getPublishDate())) {
                //   System.out.println("IN SIDE IF");
                quizdata2 = this.getQuizDetails(qList.getQuizInfoList().get(i).getDoc_Id());
//                System.out.println("quizdata2 : " + quizdata2);
                Firestore firestore = FirestoreClient.getFirestore();

                DocumentReference writeResult1 = firestore.collection(collectionConstsants.QUIZ_ANS_MAIN).document(quizdata2.getQuiz_id());
                DocumentSnapshot documentSnapshot_0 = writeResult1.get().get();
                writeResult1.set(quizdata2);


                ApiFuture<QuerySnapshot> future = firestore.collection(collectionConstsants.QUIZ_ANS_MAIN).get();
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                writeResult1.set(quizdata2);
                for (QueryDocumentSnapshot document : documents) {
                    if (publishDate.equals(quizdata2.getPublished_date())) {

                        ApiFuture<WriteResult> writeResult = firestore.collection(collectionConstsants.QUIZ_ANS_MAIN).document(document.getId()).delete();

                        DocumentReference writeResult2 = firestore.collection(collectionConstsants.QUIZ_ANS_MAIN).document(quizdata2.getQuiz_id());
                        DocumentSnapshot documentSnapshot_01 = writeResult2.get().get();
                        writeResult1.set(quizdata2);
                    }


                    break;
                }
            }
        }

    }

    public AnnouncementList addAnnoucement() {
        Firestore firestore = FirestoreClient.getFirestore();
        Announcement announcement = new Announcement();
        AnnouncementList announcementList = new AnnouncementList();
        List<Announcement> newAnnouncementList = new ArrayList();
        announcement.setDept_code("RBDA");
        announcement.setEntry_date("2022-09-14 13:08:02");
        announcement.setFilenetID("F0EE3A83-0000-C01A-AE8F-A214C59714ED");
        announcement.setTitle("Launch of NR Secured Credit Cards");

        newAnnouncementList.add(announcement);
        announcementList.setData(newAnnouncementList);

        ApiFuture<WriteResult> collectionApiFuture = firestore.collection("announcementTest").document("14092022").set(announcementList);
        System.out.println("compelete");
        return announcementList;

    }
}


