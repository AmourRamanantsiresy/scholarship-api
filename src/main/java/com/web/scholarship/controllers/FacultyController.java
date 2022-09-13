package com.web.scholarship.controllers;

import com.web.scholarship.models.Faculty;
import com.web.scholarship.services.FacultyService;
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
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/faculty")
public class FacultyController {
    private FacultyService facultyService;

    @GetMapping("")
    public DataFormat<Faculty> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order
    ) {
        return facultyService.getAll(page, size, order);
    }


    @GetMapping("/search")
    public DataFormat<Faculty> searchFaculties(
            @RequestParam String searchValue,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order
    ) {
        return facultyService.searchByFaculty(searchValue, page, size, order);
    }

    @GetMapping("/{id}")
    public Optional<Faculty> getOne(
            @PathVariable Long id
    ) {
        return facultyService.getOne(id);
    }


    @PutMapping("")
    public List<Faculty> createOrUpdate(
            @RequestBody List<Faculty> faculties
    ) {
        return facultyService.createOrUpdate(faculties);
    }
}
