package org.serf.ApplicantPortal.services;

import org.serf.ApplicantPortal.domain.Applicant;
import org.serf.ApplicantPortal.domain.Subject;
import org.serf.ApplicantPortal.repository.ApplicantRepo;
import org.serf.ApplicantPortal.util.ApplicantComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ApplicantService {

    private Logger logger = LoggerFactory.getLogger(ApplicantService.class);

    private final ApplicantRepo applicantRepo;
    private final SubjectService subjectService;

    @Autowired
    public ApplicantService(ApplicantRepo applicantRepo, SubjectService subjectService) {
        this.applicantRepo = applicantRepo;
        this.subjectService = subjectService;
    }

    @Transactional
    public void saveApplicant(Applicant applicant) {
        logger.debug("Applicant save success: " + applicant.getId() + " " + applicant.getFirstName() + " " + applicant.getLastName());
        applicantRepo.save(applicant);
    }

//    @Transactional
//    public void updateApplicantSubjects(Applicant applicant) {
//        Applicant applicantToUpdate = getApplicantById(applicant.getId()).get();
//        List<Subject> subjects = applicant.getSubjects();
//        applicantToUpdate.setSubjects(subjects);

//        for (Subject subject : subjects) {
//            subject.setApplicant(applicantToUpdate);
//            subjectService.saveSubject(subject);
//        }
//        List<Applicant> applicants = applicant.getFaculty().getApplicants();
//        Collections.sort(applicants, Comparator.comparingInt(s -> s.getSubjects().stream().mapToInt(Subject::getRate).sum()));
//        int positionInRating = applicants.indexOf(applicantToUpdate) + 1;

//        int recruitmentPlan = applicant.getFaculty().getRecruitmentPlan();
//        if (positionInRating <= recruitmentPlan) {
//            applicantToUpdate.setIsApplied(true);
//            if (recruitmentPlan < applicants.size()) {
//                List<Applicant> applicantsForUpdate = new ArrayList<>();
//                for (int i = recruitmentPlan + 1; i < applicants.size(); i++) {
//                    Applicant rejectedApplicant = applicants.get(i);
//                    rejectedApplicant.setIsApplied(false);
//                    applicantsForUpdate.add(rejectedApplicant);
//                }
//                applicantRepo.saveAll(applicantsForUpdate);
//            }
//        } else {
//            applicantToUpdate.setIsApplied(false);
//        }
//        saveApplicant(applicantToUpdate);
//    }

    @Transactional
    public void updateApplicantSubjects(Applicant applicant) {
        logger.debug("Applicant update and subjects update success: " + applicant.getId() + " " + applicant.getFirstName() + " " + applicant.getLastName() + " " + applicant.getSubjects());
        Applicant applicantToUpdate = updateApplicant(applicant);
        List<Subject> subjects = applicant.getSubjects();
        updateSubjects(applicantToUpdate, subjects);
        updateApplicantStatus(applicantToUpdate);
        saveUpdatedApplicant(applicantToUpdate);
    }

    public Applicant updateApplicant(Applicant applicant) {
        logger.debug("Applicant update success: " + applicant.getId() + " " + applicant.getFirstName() + " " + applicant.getLastName());
        Applicant applicantToUpdate = getApplicantById(applicant.getId()).get();
        applicantToUpdate.setFirstName(applicant.getFirstName());
        applicantToUpdate.setLastName(applicant.getLastName());
        applicantToUpdate.setBirthYear(applicant.getBirthYear());
        applicantToUpdate.setFaculty(applicant.getFaculty());
        applicantToUpdate.setSubjects(applicant.getSubjects());
        applicantToUpdate.setProfileImage(applicant.getProfileImage());
        applicantToUpdate.setImageName(applicant.getImageName());
        applicantToUpdate.setIsApplied(applicant.getIsApplied());
        return applicantToUpdate;
    }


    public void updateSubjects(Applicant applicantToUpdate, List<Subject> subjects) {
        logger.debug("Applicant update subjects success: " + applicantToUpdate.getId() + " " + applicantToUpdate.getFirstName() + " " + applicantToUpdate.getLastName() + " " + applicantToUpdate.getSubjects());
        applicantToUpdate.setSubjects(subjects);
        for (Subject subject : subjects) {
            subject.setApplicant(applicantToUpdate);
            subjectService.saveSubject(subject);
        }
    }

    public int calculatePositionInRating(Applicant applicantToUpdate) {
        List<Applicant> applicants = applicantToUpdate.getFaculty().getApplicants();
        applicants.sort(new ApplicantComparator());
        return applicants.indexOf(applicantToUpdate) + 1;
    }

    public void updateApplicantStatus(Applicant applicantToUpdate) {
        int recruitmentPlan = applicantToUpdate.getFaculty().getRecruitmentPlan();
        int positionInRating = calculatePositionInRating(applicantToUpdate);
        if (positionInRating <= recruitmentPlan) {
            applicantToUpdate.setIsApplied(true);
            updateRejectedApplicants(applicantToUpdate.getFaculty().getApplicants(), recruitmentPlan);
        } else {
            applicantToUpdate.setIsApplied(false);
        }
    }

    public void updateRejectedApplicants(List<Applicant> applicants, int recruitmentPlan) {
        if (recruitmentPlan < applicants.size()) {
            List<Applicant> applicantsForUpdate = new ArrayList<>();
            for (int i = recruitmentPlan; i < applicants.size(); i++) {
                Applicant rejectedApplicant = applicants.get(i);
                rejectedApplicant.setIsApplied(false);
                applicantsForUpdate.add(rejectedApplicant);
            }
            applicantRepo.saveAll(applicantsForUpdate);
        }
    }

    public void saveUpdatedApplicant(Applicant applicantToUpdate) {
        logger.debug("Applicant save success: " + applicantToUpdate.getId() + " " + applicantToUpdate.getFirstName() + " " + applicantToUpdate.getLastName());
        saveApplicant(applicantToUpdate);
    }

    public Optional<Applicant> getApplicantById(int id) {
        logger.debug("Get applicant by ID success");
        return applicantRepo.findById(id);
    }

    public List<Applicant> getAllApplicants() {
        logger.debug("Get all applicants");
        return applicantRepo.findAll();
    }

    @Transactional
    public void updateApplicantById(int id, Applicant updatedApplicant) {
        updatedApplicant.setId(id);
        applicantRepo.save(updatedApplicant);
    }
}
