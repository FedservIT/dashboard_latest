package com.fedserv.pulse_dashboard.controller;

import com.fedserv.pulse_dashboard.model.Explore;
import com.fedserv.pulse_dashboard.model.ExploreData;
import com.fedserv.pulse_dashboard.model.Notification;
import com.fedserv.pulse_dashboard.model.TodayList;
import com.fedserv.pulse_dashboard.service.FedCornerService;
import com.fedserv.pulse_dashboard.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
@RestController
@RequestMapping("/pulse")

public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/getNotifications")
    public String getNotifications(@RequestParam String title,String message) throws ExecutionException, InterruptedException {
        System.out.println("dfvvfdsv");
        return notificationService.broadcast(title,message);
    }

}
