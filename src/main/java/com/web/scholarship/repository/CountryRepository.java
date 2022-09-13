package com.web.scholarship.repository;

import com.web.scholarship.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByNameContainsIgnoreCase(String country);
    Country findCountryByNameIgnoreCase(String name);
}
