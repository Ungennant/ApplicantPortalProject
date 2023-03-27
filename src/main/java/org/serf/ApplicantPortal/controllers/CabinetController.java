package org.serf.ApplicantPortal.controllers;

import org.serf.ApplicantPortal.domain.User;
import org.serf.ApplicantPortal.services.ApplicantService;
import org.serf.ApplicantPortal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Optional;

@Controller
public class CabinetController {

    private final ApplicantService applicantService;
    private final UserService userService;

    @Autowired
    public CabinetController(ApplicantService applicantService, UserService userServicel) {
        this.applicantService = applicantService;
        this.userService = userServicel;
    }

    @GetMapping("/cabinet")
    public String userCabinet(Model model, Principal principal){

        String currUsername = principal.getName();
        Optional<User> user = userService.getUserByUserName(currUsername);
        model.addAttribute("user", user.orElse(null));

        return "cabinet";
    }

    @GetMapping()
    public String showApplicants(Model model){
        model.addAttribute("applicants", applicantService.getAllApplicants());
        return "cabinet";
    }

    @GetMapping("/{id}")
    public String showApplicant(@PathVariable("id") int id, Model model){
        model.addAttribute("applicant", applicantService.getApplicantById(id));
        return "show";
    }
}
