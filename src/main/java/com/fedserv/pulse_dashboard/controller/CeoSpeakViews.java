package com.fedserv.pulse_dashboard.controller;

import com.fedserv.pulse_dashboard.model.CeoSpeakLike;
import com.fedserv.pulse_dashboard.service.CeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
@RestController
@RequestMapping("/pulse")

public class CeoSpeakViews {
    @Autowired
    CeoService ceoService;
    @GetMapping("/ceoSpeakViews")
    public int ceoSpeakViews(@RequestParam String speak_id) throws ExecutionException, InterruptedException {
        return ceoService.ceoSpeakViews(speak_id);
    }

}
