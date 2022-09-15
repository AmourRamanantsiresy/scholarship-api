package com.web.scholarship.controllers;

import com.web.scholarship.models.Language;
import com.web.scholarship.services.LanguageService;
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
@RequestMapping("/language")
public class LanguageController {
    private LanguageService languageService;

    @GetMapping("")
    public DataFormat<Language> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "-1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order
    ) {
        return languageService.getAll(page, size, order);
    }

    @GetMapping("/{id}")
    public Optional<Language> getOne(
            @PathVariable Long id
    ) {
        return languageService.getOne(id);
    }

    @GetMapping("/search")
    public DataFormat<Language> searchLanguages(
            @RequestParam String searchValue,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order
    ) {
        return languageService.searchByLanguage(searchValue, page, size,order);
    }

    @PutMapping("")
    public List<Language> createOrUpdate(
            @RequestBody List<Language> languages
    ) {
        return languageService.createOrUpdate(languages);
    }
}
