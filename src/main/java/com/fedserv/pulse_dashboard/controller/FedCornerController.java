package com.fedserv.pulse_dashboard.controller;

import com.fedserv.pulse_dashboard.model.Federal;
import com.fedserv.pulse_dashboard.model.FederalData;
import com.fedserv.pulse_dashboard.service.FedCornerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pulse")

public class FedCornerController {
    @Autowired
    FedCornerService fedCornerService;

    @GetMapping("/getFederalExploreDetails")
    public FederalData getFederalExplorePageAndImageList() throws ExecutionException, InterruptedException {
        return fedCornerService.getFederalExplorePageAndImageList();
    }

    @GetMapping("/getFedExploreDetailsByPageName")
    public Federal getFedExploreDetailsByPageName(@RequestParam String pageName) throws ExecutionException, InterruptedException {
        return fedCornerService.getFedExploreDetailsByPageName(pageName);
    }

    @GetMapping("/deleteFedDocumentByPageName")
    public String deleteFedDocumentByPageName(@RequestParam String pageType) throws ExecutionException, InterruptedException {
        return fedCornerService.deleteFedDocumentByPageName(pageType);
    }

}
