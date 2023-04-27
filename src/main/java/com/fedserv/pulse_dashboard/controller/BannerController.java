package com.fedserv.pulse_dashboard.controller;

import com.fedserv.pulse_dashboard.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pulse")

public class BannerController {
//    @Value("${filepath}")
//    public String filepath;


@Autowired
BannerService bannerService;
    @PostMapping("/bannerImage")
    public String uploaData(@RequestParam("file") MultipartFile multipartFile,@RequestParam String date) throws IOException {

//        InputStream resourceInputStream = new FileInputStream(BannerController.class.getClassLoader().getResource("").getPath()+"..\\..\\src\\main\\resources\\static\\ServiceAccount.json");
        return bannerService.bannerUpload(multipartFile,date); }

        @GetMapping("/installedList")
    public List<String> installData() throws ExecutionException, InterruptedException {
            return  bannerService.installDta();
        }

    @GetMapping("/fameDb")
    public List<String> fameDbCount() throws ExecutionException, InterruptedException, IOException {
        return  bannerService.fameDbCount();
    }

    @GetMapping("/comparison")
    public String pfNmCompare() throws ExecutionException, InterruptedException, IOException {
        return  bannerService.pfNmCompare();
    }
    @GetMapping("/Iphonecomparison")
    public String iphoneDetails() throws ExecutionException, InterruptedException, ParseException {
        return  bannerService.iphoneDetails();
    }




}
