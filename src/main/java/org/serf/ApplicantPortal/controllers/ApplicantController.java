package org.serf.ApplicantPortal.controllers;

import org.serf.ApplicantPortal.domain.Applicant;
import org.serf.ApplicantPortal.services.ApplicantService;
import org.serf.ApplicantPortal.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;
    private final FacultyService facultyService;

    @Autowired
    public ApplicantController(ApplicantService applicantService, FacultyService facultyService) {
        this.applicantService = applicantService;
        this.facultyService = facultyService;
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("applicants", applicantService.getAllApplicants());
        return "applicants/all";
    }

    @GetMapping("/new")
    public String newApplicant(@ModelAttribute("applicant") Applicant applicant, Model model) {
        model.addAttribute("faculties", facultyService.getAllFaculties());
        return "applicants/new";
    }

    @PostMapping()
    public String addApplicant(@ModelAttribute("applicant") Applicant applicant, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "applicants/new"; //TODO
        applicantService.registerApplicant(applicant);
        return "redirect:/applicants/all";
    }
}
