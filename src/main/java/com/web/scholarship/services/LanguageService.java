package com.web.scholarship.services;

import com.web.scholarship.models.Language;
import com.web.scholarship.repository.LanguageRepository;
import com.web.scholarship.utils.utils.Order;
import com.web.scholarship.utils.pagination.DataFormat;
import com.web.scholarship.utils.pagination.DataParser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LanguageService {
    private LanguageRepository languageRepository;

    public DataFormat<Language> getAll(int page, int size, Order order) {
        DataParser<Language> parse = new DataParser<>(languageRepository.findAll(Sort.by(Sort.Direction.fromString(order.toString()), "name")));
        return parse.format(page, size);
    }

    public Optional<Language> getOne(Long id) {
        return languageRepository.findById(id);
    }

    public DataFormat<Language> searchByLanguage(String name, int page, int size, Order order) {
        DataParser<Language> parse = new DataParser<>(languageRepository.findAllByNameContainsIgnoreCase(name).stream()
                .sorted((o1, o2) -> {
                    if (order == Order.ASC) {
                        return o1.getName().compareTo(o2.getName());
                    }
                    return o2.getName().compareTo(o1.getName());
                }).toList()
        );
        return parse.format(page, size);
    }

    @Transactional
    public List<Language> createOrUpdate(List<Language> applications) {
        return languageRepository.saveAll(applications);
    }
}
