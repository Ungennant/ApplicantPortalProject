package org.serf.ApplicantPortal.services;

import org.serf.ApplicantPortal.domain.Applicant;
import org.serf.ApplicantPortal.domain.Subject;
import org.serf.ApplicantPortal.repository.ApplicantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ApplicantService {

    private final ApplicantRepo applicantRepo;
    private final SubjectService subjectService;

    @Autowired
    public ApplicantService(ApplicantRepo applicantRepo, SubjectService subjectService) {
        this.applicantRepo = applicantRepo;
        this.subjectService = subjectService;
    }

    @Transactional
    public void registerApplicant(Applicant applicant) {
        applicantRepo.save(applicant);
    }

    @Transactional
    public void updateApplicantSubjects(Applicant applicant) {
        Applicant applicantToUpdate = getApplicantById(applicant.getId()).get();
        List<Subject> subjects = applicant.getSubjects();
        applicantToUpdate.setSubjects(subjects);

        for (Subject subject : subjects) {
            subject.setApplicant(applicantToUpdate);
            subjectService.saveSubject(subject);
        }
        List<Applicant> applicants = applicant.getFaculty().getApplicants();
        Collections.sort(applicants, Comparator.comparingInt(s -> s.getSubjects().stream().mapToInt(Subject::getRate).sum()));
        int positionInRating = applicants.indexOf(applicantToUpdate) + 1;

        int recruitmentPlan = applicant.getFaculty().getRecruitmentPlan();
        if (positionInRating <= recruitmentPlan) {
            applicantToUpdate.setIsApplied(true);
            if (recruitmentPlan < applicants.size()) {
                List<Applicant> applicantsForUpdate = new ArrayList<>();
                for (int i = recruitmentPlan + 1; i < applicants.size(); i++) {
                    Applicant rejectedApplicant = applicants.get(i);
                    rejectedApplicant.setIsApplied(false);
                    applicantsForUpdate.add(rejectedApplicant);
                }
                applicantRepo.saveAll(applicantsForUpdate);
            }
        } else {
            applicantToUpdate.setIsApplied(false);
        }
        registerApplicant(applicantToUpdate);
    }


    public Optional<Applicant> getApplicantById(int id) {
        return applicantRepo.findById(id);
    }

    public List<Applicant> getAllApplicants() {
        return applicantRepo.findAll();
    }
}
