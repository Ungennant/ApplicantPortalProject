package org.serf.ApplicantPortal.repository;

import org.serf.ApplicantPortal.domain.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepo extends JpaRepository<Applicant, Integer> {

}
