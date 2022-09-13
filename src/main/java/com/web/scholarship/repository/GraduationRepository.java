package com.web.scholarship.repository;

import com.web.scholarship.models.Graduation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GraduationRepository extends JpaRepository<Graduation, Long> {
    List<Graduation> findAllByNameContainsIgnoreCase(String name);
}
