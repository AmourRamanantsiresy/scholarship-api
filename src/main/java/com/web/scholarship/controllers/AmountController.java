package com.web.scholarship.controllers;

import com.web.scholarship.models.Amount;
import com.web.scholarship.services.AmountService;
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
@RequestMapping("/amount")
public class AmountController {
    private AmountService amountService;

    @GetMapping("")
    public DataFormat<Amount> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order
    ) {
        return amountService.getAll(page, size, order);
    }

    @GetMapping("/{id}")
    public Optional<Amount> getOne(
            @PathVariable Long id
    ) {
        return amountService.getOne(id);
    }

    @PutMapping("")
    public List<Amount> createOrUpdate(
            @RequestBody List<Amount> amounts
    ) {
        return amountService.createOrUpdate(amounts);
    }
}

