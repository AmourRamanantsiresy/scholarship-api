package com.web.scholarship.controllers;

import com.web.scholarship.models.mapper.models.Candidate;
import com.web.scholarship.services.CandidateService;
import com.web.scholarship.models.enums.CandidateSearchType;
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
@RequestMapping("/candidate")
@CrossOrigin(origins = "*")
public class CandidateController {
    private CandidateService candidateService;

    @GetMapping("")
    public DataFormat<Candidate> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order,
            @RequestParam(name = "orderBy", required = false, defaultValue = "FISRST_NAME")CandidateSearchType orderBy
            ) {
        return candidateService.getAll(page, size, order, orderBy);
    }

    @GetMapping("/{id}")
    public Candidate getOne(
            @PathVariable Long id
    ) {
        return candidateService.getOne(id);
    }

    @GetMapping("/search")
    public DataFormat<Candidate> searchCandidates(
            @RequestParam(name = "q") String searchValue,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "by", required = false, defaultValue = "FIRST_NAME") CandidateSearchType by
    ) {
        return candidateService.searchBy(searchValue, by, page, size);
    }

    @PutMapping("")
    public List<Candidate> createOrUpdate(
            @RequestBody List<com.web.scholarship.models.Candidate> candidates
    ) {
        return candidateService.createCandidate(candidates);
    }
}
