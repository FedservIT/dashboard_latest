package com.fedserv.pulse_dashboard.controller;



import com.fedserv.pulse_dashboard.model.RadioList;
import com.fedserv.pulse_dashboard.model.RadioMangement;
import com.fedserv.pulse_dashboard.model.Radio_Details;
import com.fedserv.pulse_dashboard.service.RadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pulse")
public class RadioController {
    @Autowired
    RadioService radioService;

//    public RadioList updateRadioFile(@RequestBody RadioList radioList){
//        return radioService.updateRadioFile(radioList);
//    }
//@GetMapping("/updatejson")
//    public Object updateRadioFile(@RequestParam String fileName) throws IOException {
//        return radioService.download(fileName);
//    }

    @PostMapping("/radioString")
    public String radioLive(@RequestBody Radio_Details radio_details) throws ExecutionException, InterruptedException, IOException {
        return radioService.radioLive(radio_details);
    }


}
