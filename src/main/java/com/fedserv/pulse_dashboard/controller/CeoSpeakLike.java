package com.fedserv.pulse_dashboard.controller;

import com.fedserv.pulse_dashboard.service.CeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pulse")
public class CeoSpeakLike {
    @Autowired
    CeoService ceoService;
    @GetMapping("/ceoSpeakLikes")
    public String ceoSpeakLikes() throws ExecutionException, InterruptedException {
        return ceoService.ceoSpeakLikes();
    }



}
