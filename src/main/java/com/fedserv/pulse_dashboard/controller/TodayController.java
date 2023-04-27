package com.fedserv.pulse_dashboard.controller;

import com.fedserv.pulse_dashboard.model.*;
import com.fedserv.pulse_dashboard.service.TodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pulse")
public class TodayController {
    @Value("${filepath}")
    public String filepath;
    @Autowired
    TodayService todayService;

//    @PostMapping("/add Common TodayInfo")
//    public TodayForYou saveTodayData(@RequestBody TodayForYou todayForYou) throws ExecutionException, InterruptedException {
//        return todayService.addTodayInfo(todayForYou); }

        @PostMapping("/image")
    public String upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return todayService.upload(multipartFile); }

    @GetMapping("/getJokes and qoutes Info")
    public TodayList fetchJokes()throws ExecutionException, InterruptedException
    { return todayService.getJokesInfo(); }

    @PostMapping("/addSmiles")
    public Smiles addSmiles(@RequestBody Smiles smiles) throws ExecutionException, InterruptedException {
        return todayService.addSmiles(smiles);
    }

    @GetMapping("/getTrivia")
      public TodayList getTrivia() throws ExecutionException, InterruptedException {
       return todayService.getTrivia();
      }

    @GetMapping("/getDidYouKnow")
    public TodayList getDidYouKnow() throws ExecutionException, InterruptedException {
        return todayService.getDidYouKnow();
    }

    @PostMapping("/addTrivia")
    public String addTrivia(@RequestBody TodayForYou todayForYou,@RequestParam String date,@RequestParam String ColletionName) throws ExecutionException, InterruptedException, ParseException {
        return todayService.addTrivia(date,ColletionName,todayForYou);
    }

   @PostMapping("/readExcel")
    public ExcelModelList update(@RequestParam("file") MultipartFile multipartFile,@RequestParam String collName) throws IOException {
            return todayService.update(multipartFile,collName);
 }

}
