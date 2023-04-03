package org.serf.ApplicantPortal.services;

import org.serf.ApplicantPortal.domain.User;
import org.serf.ApplicantPortal.repository.UserRepo;
import org.serf.ApplicantPortal.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepo userRepo;

    @Autowired
    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Applicant load by username success");
        Optional<User> user = userRepo.findByUsername(username);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User with such username not found!");

        return new CustomUserDetails(user.get());
    }
}
