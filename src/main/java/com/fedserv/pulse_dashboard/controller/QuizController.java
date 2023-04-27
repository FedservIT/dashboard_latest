package com.fedserv.pulse_dashboard.controller;

import com.fedserv.pulse_dashboard.model.*;
import com.fedserv.pulse_dashboard.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pulse")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/addQuizData")
    public QuizData saveData(@RequestBody QuizData quizData) throws ExecutionException, InterruptedException, ParseException {
        return quizService.addQuizData(quizData);
    }

    @GetMapping ("/timer")
    public void dailyQuiz() throws ExecutionException, InterruptedException, ParseException {
        quizService.quizTimer();
    }

    @GetMapping("/getQuizDetails")
    public QuizData getQuizData(@RequestParam String quiz_id) throws ExecutionException, InterruptedException {
        return quizService.getQuizDetails(quiz_id);
    }

    @PostMapping("/addQuizInfo")
    public QuizInfo saveInfoData(@RequestBody QuizInfo quizInfo) throws ExecutionException, InterruptedException {
        return quizService.addQuizInfo(quizInfo);

    }
    @GetMapping("/getQuizInfo")
    public QuizList getQuizInfo () throws ExecutionException, InterruptedException, ParseException {
        return quizService.getQuizInfo();
    }

    @GetMapping("/getQuizContent")
    public QuizInfo getQuizData() throws ExecutionException, InterruptedException {
        return quizService.getQuizContent();
    }
    @GetMapping("/getQuizWinnerFirst")
    public QuizWinner getQuizWinnerFirst() throws ExecutionException, InterruptedException
    {
        return quizService.getQuizWinnerFirst();
    }
    @GetMapping("/getQuizWinnerSecond")
    public QuizWinner getQuizWinnerSecond()  throws ExecutionException, InterruptedException
    {
        return quizService.getQuizWinnerSecond();
    }
    @GetMapping("/getQuizWinnerThird")
    public QuizWinner getQuizWinnerThird()  throws ExecutionException, InterruptedException
    {
        return quizService.getQuizWinnerThird();
    }
    @GetMapping("/getQuizMasterAnswers")
    public QuizScoreArray getQuizMasterAnswer(@RequestParam String quiz_id) throws ExecutionException, InterruptedException {
        return quizService.getQuizMasterAnswer(quiz_id);
    }
    //getQuizMasterNames
    @GetMapping("/getQuizMasterNames")
    public String getQuizMasterNames(@RequestParam String empID) throws ExecutionException, InterruptedException {
        return quizService.getQuizMasterNames(empID);
    }
    //getQuizMasterImages
    @GetMapping("/getQuizMasterImages")
    public String getQuizMasterImages(@RequestParam String empID) throws ExecutionException, InterruptedException {
        return quizService.getQuizMasterImages(empID);
    }
    @PostMapping("/addQuizFirst")
    public QuizWinner addFirstData(@RequestBody QuizWinner quizWinner) throws ExecutionException, InterruptedException {
        return quizService.addQuizFirst(quizWinner);
    }
    @PostMapping("/addQuizSecond")
    public QuizWinner addSecondData(@RequestBody QuizWinner quizWinner) throws ExecutionException, InterruptedException {
        return quizService.addQuizSecond(quizWinner);
    }
    @PostMapping("/addQuizThird")
    public QuizWinner addThirdData(@RequestBody QuizWinner quizWinner) throws ExecutionException, InterruptedException {
        return quizService.addQuizThird(quizWinner);
    }
    @PostMapping("/drsisyaAnnoucement")
    public AnnouncementList addDrisya(){
        return quizService.addAnnoucement();
    }

}