package com.web.scholarship.repository;

import com.web.scholarship.models.Candidate;
import com.web.scholarship.models.StudyLevel;
import com.web.scholarship.models.dbUser.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findAllBySchoolOriginContainsIgnoreCase(String school);

    List<Candidate> findAllByStudyLevel(StudyLevel studyLevel);

    List<Candidate> findAllByFirstNameContainsIgnoreCase(String name);

    List<Candidate> findAllByLastNameIsLikeIgnoreCase(String name);

    Candidate findCandidateByCredentials(DBUser credential);
}
