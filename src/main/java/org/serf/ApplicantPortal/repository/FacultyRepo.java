package org.serf.ApplicantPortal.repository;

import org.serf.ApplicantPortal.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepo extends JpaRepository<Faculty, Integer> {
}
