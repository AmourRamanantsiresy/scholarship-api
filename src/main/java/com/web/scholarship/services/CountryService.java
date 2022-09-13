package com.web.scholarship.services;

import com.web.scholarship.models.Country;
import com.web.scholarship.models.Language;
import com.web.scholarship.models.mapper.CountryMapper;
import com.web.scholarship.repository.CountryRepository;
import com.web.scholarship.repository.LanguageRepository;
import com.web.scholarship.models.enums.CountrySearchType;
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
public class CountryService {
    private LanguageRepository languageRepository;
    private CountryRepository countryRepository;

    public DataFormat<com.web.scholarship.models.mapper.models.Country> getAll(int page, int size, Order order, CountrySearchType by) {
        DataParser<com.web.scholarship.models.mapper.models.Country> parse;
        String orderBy = by == CountrySearchType.COUNTRY ? "name" : "languages";
        parse = new DataParser<>(
                CountryMapper.parseList(
                        countryRepository.findAll(Sort.by(Sort.Direction.fromString(order.toString()), orderBy))
                )
        );
        return parse.format(page, size);
    }

    public com.web.scholarship.models.mapper.models.Country getOne(Long id) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            return CountryMapper.parse(country.get());
        }
        throw new RuntimeException("Resource not found");
    }

    public DataFormat<com.web.scholarship.models.mapper.models.Country> searchCountry(String name, CountrySearchType type, int page, int size) {
        DataParser<com.web.scholarship.models.mapper.models.Country> parse;
        List<Country> res;
        if (type == CountrySearchType.COUNTRY) {
            res = searchByCountry(name);
        } else {
            res = searchByLanguage(name);
        }
        List<com.web.scholarship.models.mapper.models.Country> restRes = CountryMapper.parseList(res);
        parse = new DataParser<>(restRes);
        return parse.format(page, size);
    }

    @Transactional
    public List<com.web.scholarship.models.mapper.models.Country> createOrUpdate(List<Country> applications) {
        return CountryMapper.parseList(countryRepository.saveAll(applications));
    }

    public List<Country> searchByCountry(String name) {
        return countryRepository.findAllByNameContainsIgnoreCase(name);
    }

    public List<Country> searchByLanguage(String language) {
        Language languageRef = languageRepository.findLanguageByNameIgnoreCase(language);
        return countryRepository.findAll().stream().filter(e -> e.getLanguages().contains(languageRef)).toList();
    }
}
