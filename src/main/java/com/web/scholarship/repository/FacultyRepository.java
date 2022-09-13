package com.web.scholarship.repository;

import com.web.scholarship.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findAllByNameContainsIgnoreCase(String name);
}
