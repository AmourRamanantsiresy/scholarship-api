package com.web.scholarship.controllers;

import com.web.scholarship.models.mapper.models.CandidateOfCandidate;
import com.web.scholarship.models.mapper.models.ScholarshipOfCandidate;
import com.web.scholarship.services.ApplicationService;
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


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/application")
public class ApplicationController {
    private ApplicationService applicationService;

    @GetMapping("/candidate/{id}")
    public DataFormat<ScholarshipOfCandidate> getAllScholarshipsForCandidate(
            @PathVariable Long id,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size
    ){
        return applicationService.getAllScholarshipForCandidate(id, page, size);
    }

    @GetMapping("/scholarship/{id}")
    public DataFormat<CandidateOfCandidate> getAllCandidateForScholarship(
            @PathVariable Long id,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size
    ){
        return applicationService.getAllCandidateForScholarship(id, page, size);
    }

    @PutMapping("/close")
    public String closeApplication(
            @RequestBody com.web.scholarship.models.Scholarship scholarship
            ){
        return applicationService.close(scholarship);
    }

    @PutMapping("/apply")
    public String closeApplication(
            @RequestBody com.web.scholarship.models.Scholarship scholarship,
            @RequestBody com.web.scholarship.models.Candidate candidate
    ){
        return applicationService.apply(scholarship, candidate);
    }

    @PutMapping("/accept")
    public String acceptApplication(
            @RequestBody com.web.scholarship.models.Scholarship scholarship,
            @RequestBody com.web.scholarship.models.Candidate candidate
    ){
        return applicationService.acceptApplication(scholarship, candidate);
    }
}