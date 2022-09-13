package com.web.scholarship.controllers;

import com.web.scholarship.models.Graduation;
import com.web.scholarship.services.GraduationService;
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
@RequestMapping("/graduation")
public class GraduationController {
    private GraduationService graduationService;

    @GetMapping("")
    public DataFormat<Graduation> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order
    ) {
        return graduationService.getAll(page, size, order);
    }

    @GetMapping("/{id}")
    public Optional<Graduation> getOne(
            @PathVariable Long id
    ) {
        return graduationService.getOne(id);
    }

    @GetMapping("/search")
    public DataFormat<Graduation> searchGraduations(
            @RequestParam String searchValue,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order
    ) {
        return graduationService.searchByGraduation(searchValue, page, size, order);
    }

    @PutMapping("")
    public List<Graduation> createOrUpdate(
            @RequestBody List<Graduation> graduations
    ) {
        return graduationService.createOrUpdate(graduations);
    }
}
