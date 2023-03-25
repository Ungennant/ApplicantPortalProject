package org.serf.ApplicantPortal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CabinetController {

    @GetMapping("/cabinet")
    public String userCabinet(){
        return "cabinet";
    }
}
