package org.serf.ApplicantPortal.services;

import org.serf.ApplicantPortal.domain.Faculty;
import org.serf.ApplicantPortal.repository.FacultyRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepo facultyRepo;

    @Autowired
    public FacultyService(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }

    public List<Faculty> getAllFaculties(){
        logger.debug("Get all faculties");
        return facultyRepo.findAll();
    }
}
