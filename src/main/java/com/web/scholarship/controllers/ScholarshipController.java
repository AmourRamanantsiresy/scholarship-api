package com.web.scholarship.controllers;

import com.web.scholarship.models.mapper.models.Scholarship;
import com.web.scholarship.models.enums.ScholarshipSearchType;
import com.web.scholarship.services.ScholarshipService;
import com.web.scholarship.utils.pagination.DataFormat;
import com.web.scholarship.utils.utils.Order;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/scholarship")
public class ScholarshipController {
    private ScholarshipService scholarshipService;

    @GetMapping("/{id}")
    public Scholarship getOne(
            @PathVariable Long id
    ) {
        return scholarshipService.getOne(id);
    }

    @GetMapping("")
    public DataFormat<Scholarship> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order,
            @RequestParam(name = "by", required = false, defaultValue = "UNIVERSITY") ScholarshipSearchType by
    ) {
        return scholarshipService.getAll(page, size, order, by);
    }

    @GetMapping("/search")
    public DataFormat<Scholarship> searchScholarships(
            @RequestParam(name = "q") String q,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "by", required = false, defaultValue = "UNIVERSITY") ScholarshipSearchType by
    ) {
        return scholarshipService.searchScholarship(by, q, page, size);
    }



    @GetMapping("/search/creation")
    public DataFormat<Scholarship> searchByDeadline(
            @RequestParam(name = "beginning") Date beginning,
            @RequestParam(name = "ending") Date ending,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size
    ){
        return scholarshipService.searchScholarshipByCreation(beginning, ending, page, size);
    }

    @GetMapping("/search/amount")
    public DataFormat<Scholarship> searchByAmount(
            @RequestParam(name = "amount") int amount,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size
    ){
        return scholarshipService.searchScholarshipByAmount(amount, page, size);
    }

    @GetMapping("/search/duration")
    public DataFormat<Scholarship> searchByDuration(
            @RequestParam(name = "duration1") int duration1,
            @RequestParam(name = "duration2") int duration2,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size
    ){
        return scholarshipService.searchScholarshipByDuration(duration1, duration2, page, size);
    }

    @PutMapping("")
    public List<Scholarship> createOrUpdate(
            @RequestBody List<com.web.scholarship.models.Scholarship> scholarships
    ) {
        return scholarshipService.createOrUpdate(scholarships);
    }
}
