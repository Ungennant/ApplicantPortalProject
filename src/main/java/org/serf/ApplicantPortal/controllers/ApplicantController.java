package org.serf.ApplicantPortal.controllers;

import org.serf.ApplicantPortal.domain.Applicant;
import org.serf.ApplicantPortal.services.ApplicantService;
import org.serf.ApplicantPortal.services.FacultyService;
import org.serf.ApplicantPortal.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


@Controller
@RequestMapping("/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;
    private final FacultyService facultyService;
    private final SubjectService subjectService;

    @Autowired
    public ApplicantController(ApplicantService applicantService, FacultyService facultyService, SubjectService subjectService) {
        this.applicantService = applicantService;
        this.facultyService = facultyService;

        this.subjectService = subjectService;
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
    public String addApplicant(@ModelAttribute("applicant") Applicant applicant, @RequestParam("image") MultipartFile multipartFile, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors())
            return "applicants/new";

        applicant.setProfileImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        applicant.setImageName(multipartFile.getOriginalFilename());
        applicantService.saveApplicant(applicant);
        return "redirect:/applicants/all";
    }

    @PostMapping("/marks")
    public String updateApplicantSubjects(@ModelAttribute("applicant") Applicant applicant, BindingResult bindingResult) {
        applicantService.updateApplicantSubjects(applicant);
        return "redirect:/applicants/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("applicant", applicantService.findApplicantById(id).get());
        return "applicants/show";
    }
}
