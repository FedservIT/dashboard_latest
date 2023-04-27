package com.fedserv.pulse_dashboard.controller;
import com.fedserv.pulse_dashboard.model.Explore;
import com.fedserv.pulse_dashboard.model.ExploreData;
import com.fedserv.pulse_dashboard.service.ExploreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pulse")

public class ExploreController {
    @Value("${filepath}")
    public String filepath;

    @Autowired
    ExploreService exploreService;


    @GetMapping("/getExploreDetails")
    public ExploreData getExplorePageAndImageList() throws ExecutionException, InterruptedException {
        return exploreService.getExplorePageAndImageList();
    }

    @PostMapping("/addDocumentData")
    public String saveData(@RequestBody Explore explore) throws ExecutionException, InterruptedException {
        return exploreService.addDocumentData(explore);

    }


    @PostMapping("/addRetirementData")
    public String saveRetirementData() throws ExecutionException, InterruptedException {
        return exploreService.addRetirementData();
    }
//    @GetMapping("/deleteDocument")
//    public String deleteData(@RequestParam String documentName)  {
//        return firebaseService.deleteDocument(documentName);
//    }

    //    @GetMapping("/getDocumentDetails")
//    public Explore getData(@RequestParam String documentName) throws ExecutionException, InterruptedException {
//        return firebaseService.getDocumentDetails(documentName);
//    }
    @GetMapping("/getExploreDetailsByPageName")
    public Explore getExploreDetailsByPageName(@RequestParam String pageName) throws ExecutionException, InterruptedException {
        return exploreService.getExploreDetailsByPageName(pageName);
    }
    @GetMapping("/deleteDocumentByPageName")
    public String deleteDocumentByPageName(@RequestParam String pageName) throws ExecutionException, InterruptedException {
        return exploreService.deleteDocumentByPageName(pageName);
    }
    @PostMapping("/getDownloadUrl")
    public String getDownloadUrl(@RequestParam("File") MultipartFile multipartFile) throws ExecutionException, InterruptedException, IOException {
        return exploreService.getDownloadUrl(multipartFile);
    }


}
