package com.web.scholarship.repository;

import com.web.scholarship.models.Application;
import com.web.scholarship.models.Candidate;
import com.web.scholarship.models.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByScholarship(Scholarship scholarship);

    List<Application> findAllByCandidate(Candidate candidate);

    Optional<Application> findApplicationByScholarshipAndCandidate(Scholarship scholarship, Candidate candidate);
}
