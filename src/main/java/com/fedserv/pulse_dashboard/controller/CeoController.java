package com.fedserv.pulse_dashboard.controller;


import com.fedserv.pulse_dashboard.model.CeoSpeak;
import com.fedserv.pulse_dashboard.model.CeoSpeakList;
import com.fedserv.pulse_dashboard.service.CeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pulse")
public class CeoController {

    @Autowired
    CeoService ceoService;


    @GetMapping("/getCeoSpeak")
    public CeoSpeak getCeoSpeak() throws ExecutionException, InterruptedException, ParseException {
        return ceoService.getCeoSpeak();
    }


    @PostMapping("/addCeoSpeak")
    public CeoSpeak addCeoSpeak(@RequestBody CeoSpeak ceoSpeak) throws ExecutionException, InterruptedException, ParseException {
        return ceoService.addCeoSpeak(ceoSpeak);
    }

    @GetMapping("/getCeoSpeakHistory")
    public CeoSpeakList ceoSpeakHistory() throws ExecutionException, InterruptedException {
        return ceoService.ceoSpeakHistory();
    }


}
