package com.fedserv.pulse_dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String getDashboard() {
        return "explore";
    }

//    @GetMapping("/explorer")
//    public String getExplore() {
//        return "explore";
//    }

    @GetMapping("/getExploreDocument")
    public String getExploreDocument() {
        return "explore-single";
    }

    @GetMapping("/addExplore")
    public String addExplore() {
        return "explore-add";
    }

    @GetMapping("/quiz-list")
    public String getQuizList() {
        return "quiz";
    }

    @GetMapping("/quiz-view")
    public String viewQuizList() { return "quiz-view"; }

    @GetMapping("/FedCorner")
    public String addFedCorner() { return "FedCorner"; }

    @GetMapping("/Federalimage")
    public String addFederalimage() { return "FedCorner"; }

    @GetMapping("/federalcorner")
    public String addFederalCorner() { return "federalcorner"; }



    @GetMapping("/quiz-add")
    public String addQuizList() {
        return "quiz-add";
    }

    @GetMapping("/notification")
    public String addNotification() {
        return "notification";
    }

    @GetMapping("/federalCorNew")
    public String addNewCorner() {
        return "federalCorNew";
    }

    @GetMapping("/contest")
    public String contest() {
        return "contest";
    }

    @GetMapping("/bannerImage")
    public String banner() {
        return "bannerImage";
    }
    @GetMapping("/todayforyou")
    public String todayforyou() {
        return "today-for-you";
    }

    @GetMapping("/bigcallmangement")
    public String bigcallmanagement() {
        return "bigcallmangement";
    }

    @GetMapping("/ceomessageEditor")
    public String ceomessageEditor() {
        return "ceo-message";
    }



//
//    @GetMapping("/federal")
//    public String addImage() {
//        return "federalcorner";
//    }


}
