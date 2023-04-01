package org.serf.ApplicantPortal.services;

import org.serf.ApplicantPortal.domain.Applicant;
import org.serf.ApplicantPortal.domain.User;
import org.serf.ApplicantPortal.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> getUserByUserName(String username){
        return userRepo.findByUsername(username);
    }
}
