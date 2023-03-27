package org.serf.ApplicantPortal.services;

import org.serf.ApplicantPortal.domain.Faculty;
import org.serf.ApplicantPortal.repository.FacultyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepo facultyRepo;

    @Autowired
    public FacultyService(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }

    public List<Faculty> getAllFaculties(){
        return facultyRepo.findAll();
    }
}
