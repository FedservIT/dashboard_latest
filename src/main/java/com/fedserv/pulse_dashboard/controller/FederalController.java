package com.fedserv.pulse_dashboard.controller;

import com.fedserv.pulse_dashboard.model.FederalCorner;
import com.fedserv.pulse_dashboard.service.FederalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pulse")
public class FederalController {
//    @Value("${filepath}")
//    public String filepath;
   @Autowired
   FederalService federalService;


    @PostMapping("/federalImage")
    public String uploaData(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return federalService.uploaData(multipartFile); }

@PostMapping("/addFederalCorner")
    public FederalCorner addFederalCorner(@RequestBody FederalCorner federalCorner){
        return federalService.addFederalCorner(federalCorner);
}
@GetMapping("/getFederalCorner")
    public FederalCorner getFederalCorner(@RequestParam String pageType) throws ExecutionException, InterruptedException {
    return federalService.getFederalCorner(pageType);
}
}

