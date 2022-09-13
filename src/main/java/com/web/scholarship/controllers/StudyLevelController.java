package com.web.scholarship.controllers;

import com.web.scholarship.models.StudyLevel;
import com.web.scholarship.services.StudyLevelService;
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
@RequestMapping("/study-level")
public class StudyLevelController {
    private StudyLevelService studyLevelService;

    @GetMapping("")
    public DataFormat<StudyLevel> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order
    ) {
        return studyLevelService.getAll(page, size, order);
    }

    @GetMapping("/{id}")
    public Optional<StudyLevel> getOne(
            @PathVariable Long id
    ) {
        return studyLevelService.getOne(id);
    }

    @GetMapping("/search")
    public DataFormat<StudyLevel> searchStudyLevels(
            @RequestParam String searchValue,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order
    ) {
        return studyLevelService.searchByStudyLevel(searchValue, page, size, order);
    }

    @PutMapping("")
    public List<StudyLevel> createOrUpdate(
            @RequestBody List<StudyLevel> studyLevel
    ) {
        return studyLevelService.createOrUpdate(studyLevel);
    }
}
