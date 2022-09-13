package com.web.scholarship.repository;

import com.web.scholarship.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findAllByNameContainsIgnoreCase(String name);
    Language findLanguageByNameIgnoreCase(String name);
}