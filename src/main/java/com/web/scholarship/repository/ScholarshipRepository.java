package com.web.scholarship.repository;

import com.web.scholarship.models.Country;
import com.web.scholarship.models.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    List<Scholarship> findAllByAmount_Amount(int amount);

    List<Scholarship> findAllByUniversity_Name(String name);

    List<Scholarship> findAllByFaculty_Name(String name);

    List<Scholarship> findAllByCreationDateBetween(Date beginning, Date ending);

    List<Scholarship> findAllByDurationIsBetween(int duration1, int duration2);

    List<Scholarship> findAllByUniversity_Country(Country country);
}
