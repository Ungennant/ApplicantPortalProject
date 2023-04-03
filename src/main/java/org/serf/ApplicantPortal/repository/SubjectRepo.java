package org.serf.ApplicantPortal.repository;

import org.serf.ApplicantPortal.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Integer> {

}
