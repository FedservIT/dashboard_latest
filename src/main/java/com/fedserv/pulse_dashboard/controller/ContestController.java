package com.fedserv.pulse_dashboard.controller;

import com.fedserv.pulse_dashboard.model.Contest;
import com.fedserv.pulse_dashboard.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pulse")
public class ContestController {
//    @Value("${filepath}")
//   public String filepath;
    @Autowired
    ContestService contestService;
    @PostMapping ("/addContest")
    public String addContest(@RequestBody Contest contest){
        return contestService.addContest(contest);

    }
    @GetMapping("/getContest")
    public Contest getContest(@RequestParam String docId) throws ExecutionException, InterruptedException {
        return contestService.getContest(docId);
    }

    @PostMapping("/contestImage")
    public String uploaData(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return contestService.uploaData(multipartFile); }

}
