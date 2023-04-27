package com.fedserv.pulse_dashboard.controller;


import com.fedserv.pulse_dashboard.model.WellBeing;
import com.fedserv.pulse_dashboard.model.WellBeingList;
import com.fedserv.pulse_dashboard.service.WellBeingService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pulse")
public class WellBeingController {

   @Autowired
   WellBeingService wellBeingService;
   @PostMapping("/add_image")
    public WellBeingList addWellBeing(@RequestBody WellBeingList wellBeingList)
   {
       return wellBeingService.addWellBeing(wellBeingList);
   }



}
