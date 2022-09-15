package com.web.scholarship.controllers;

import com.web.scholarship.models.mapper.models.University;
import com.web.scholarship.models.enums.UniversitySearchType;
import com.web.scholarship.services.UniversityService;
import com.web.scholarship.utils.utils.Order;
import com.web.scholarship.utils.pagination.DataFormat;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/university")
public class UniversityController {
    private UniversityService universityService;

    @GetMapping("")
    public DataFormat<University> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order,
            @RequestParam(name = "order", required = false, defaultValue = "UNIVERSITY") UniversitySearchType by
    ) {
        return universityService.getAll(page, size, order, by);
    }

    @GetMapping("/{id}")
    public University getOne(
            @PathVariable Long id
    ) {
        return universityService.getOne(id);
    }

    @GetMapping("/search")
    public DataFormat<University> searchUniversities(
            @RequestParam(name = "q") String q,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "UNIVERSITY") UniversitySearchType by
    ) {
        return universityService.searchUniversity(q, by, page, size);
    }

    @PutMapping("")
    public List<com.web.scholarship.models.University> createOrUpdate(
            @RequestBody List<com.web.scholarship.models.University> universities
    ) {
        return universityService.createOrUpdate(universities);
    }
}

