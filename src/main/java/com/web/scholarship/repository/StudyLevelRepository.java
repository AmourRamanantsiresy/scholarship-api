package com.web.scholarship.repository;

import com.web.scholarship.models.StudyLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyLevelRepository extends JpaRepository<StudyLevel, Long> {
    List<StudyLevel> findAllByNameContainsIgnoreCase(String name);
    StudyLevel findStudyLevelByNameIgnoreCase(String name);
}
