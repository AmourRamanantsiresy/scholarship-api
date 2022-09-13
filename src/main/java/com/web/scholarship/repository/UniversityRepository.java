package com.web.scholarship.repository;

import com.web.scholarship.models.Country;
import com.web.scholarship.models.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findAllByNameContainsIgnoreCase(String name);

    List<University> findAllByCountry(Country country);
}
