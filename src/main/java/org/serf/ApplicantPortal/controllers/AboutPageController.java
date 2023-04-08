package org.serf.ApplicantPortal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutPageController {

    @GetMapping("/about")
    public String adminPanel(){
        return "about";
    }
    
}
