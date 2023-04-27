package com.fedserv.pulse_dashboard.controller;

import com.fedserv.pulse_dashboard.model.AnnouncementList;
import com.fedserv.pulse_dashboard.model.BigCall;
import com.fedserv.pulse_dashboard.model.BigCallStatus;
import com.fedserv.pulse_dashboard.service.BigCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
@RestController
@RequestMapping("/pulse")
public class bigCallController {
    @Autowired
    BigCallService bigCallService;
    @GetMapping("/getBigcallUrl")
    public BigCallStatus getBigcallUrl() throws ExecutionException, InterruptedException {
        return bigCallService.getBigcallUrl();

    }


    @PostMapping("/addBigcallUrl")
    public BigCall addBigcallUrl(@RequestBody BigCall bigCall) throws ExecutionException, InterruptedException {
        return bigCallService.addBigcallUrl(bigCall);
    }
    @PostMapping("/addBigcallStatus")
    public BigCallStatus addBigcallUrl(@RequestBody BigCallStatus bigCallStatus) throws ExecutionException, InterruptedException {
        return bigCallService.addBigcallstatus(bigCallStatus);
    }



}
