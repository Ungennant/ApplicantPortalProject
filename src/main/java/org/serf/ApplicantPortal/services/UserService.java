package org.serf.ApplicantPortal.services;

import org.serf.ApplicantPortal.domain.Applicant;
import org.serf.ApplicantPortal.domain.User;
import org.serf.ApplicantPortal.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> getUserByUserName(String username){
        logger.debug("User registration success with such username: " + username);
        return userRepo.findByUsername(username);
    }
}
