package org.serf.ApplicantPortal.services;

import org.serf.ApplicantPortal.domain.Applicant;
import org.serf.ApplicantPortal.repository.ApplicantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantService {

    private final ApplicantRepo applicantRepo;

    @Autowired
    public ApplicantService(ApplicantRepo applicantRepo) {
        this.applicantRepo = applicantRepo;
    }

    @Transactional
    public void registerApplicant(Applicant applicant){
        applicantRepo.save(applicant);
    }

    public Optional<Applicant> getApplicantById(int id){
        return applicantRepo.findById(id);
    }

    public List<Applicant> getAllApplicants(){
        return applicantRepo.findAll();
    }
}
