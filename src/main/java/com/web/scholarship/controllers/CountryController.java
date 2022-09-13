package com.web.scholarship.controllers;

import com.web.scholarship.models.Country;
import com.web.scholarship.services.CountryService;
import com.web.scholarship.models.enums.CountrySearchType;
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
@RequestMapping("/country")
public class CountryController {
    private CountryService countryService;

    @GetMapping("")
    public DataFormat<com.web.scholarship.models.mapper.models.Country> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order,
            @RequestParam(name = "order", required = false, defaultValue = "COUNTRY") CountrySearchType by
    ) {
        return countryService.getAll(page, size, order, by);
    }

    @GetMapping("/{id}")
    public com.web.scholarship.models.mapper.models.Country getOne(
            @PathVariable Long id
    ) {
        return countryService.getOne(id);
    }

    @GetMapping("/search")
    public DataFormat<com.web.scholarship.models.mapper.models.Country> searchCountry(
            @RequestParam String searchValue,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "COUNTRY") CountrySearchType by
            ) {
        return countryService.searchCountry(searchValue, by, page, size);
    }

    @PutMapping("")
    public List<com.web.scholarship.models.mapper.models.Country> createOrUpdate(
            @RequestBody List<Country> country
    ) {
        return countryService.createOrUpdate(country);
    }
}
