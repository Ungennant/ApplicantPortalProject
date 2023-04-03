package org.serf.ApplicantPortal.services;

import org.serf.ApplicantPortal.domain.Subject;
import org.serf.ApplicantPortal.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubjectService {

    private final SubjectRepo subjectRepo;

    @Autowired
    public SubjectService(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    @Transactional
    public void saveSubject(Subject subject){
        subjectRepo.save(subject);
    }

}
