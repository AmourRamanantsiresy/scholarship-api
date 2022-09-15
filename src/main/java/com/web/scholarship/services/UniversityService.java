package com.web.scholarship.services;

import com.web.scholarship.models.Country;
import com.web.scholarship.models.Language;
import com.web.scholarship.models.University;
import com.web.scholarship.models.enums.UniversitySearchType;
import com.web.scholarship.models.mapper.UniversityMapper;
import com.web.scholarship.repository.CountryRepository;
import com.web.scholarship.repository.LanguageRepository;
import com.web.scholarship.repository.UniversityRepository;
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
public class UniversityService {
    private UniversityRepository universityRepository;
    private CountryRepository countryRepository;
    private LanguageRepository languageRepository;

    public DataFormat<com.web.scholarship.models.mapper.models.University> getAll(int page, int size, Order order, UniversitySearchType by) {
        String orderBy = by == UniversitySearchType.UNIVERSITY ? "name" : by.toString();
        DataParser<com.web.scholarship.models.mapper.models.University> parse = new DataParser<>(UniversityMapper.parseList(universityRepository.findAll(Sort.by(Sort.Direction.fromString(order.toString()), orderBy))));
        return parse.format(page, size);
    }

    public com.web.scholarship.models.mapper.models.University getOne(Long id) {
        Optional<University> university = universityRepository.findById(id);
        if (university.isPresent()) {
            return UniversityMapper.parse(university.get());
        }
        throw new RuntimeException("Resource not found");
    }

    public DataFormat<com.web.scholarship.models.mapper.models.University> searchUniversity(String name, UniversitySearchType type, int page, int size) {
        List<University> res;
        if (type == UniversitySearchType.UNIVERSITY) {
            res = searchByUniversity(name);
        } else if (type == UniversitySearchType.LANGUAGE) {
            res = searchByLanguage(name);
        } else {
            res = searchByCountry(name);
        }
        DataParser<com.web.scholarship.models.mapper.models.University> parse = new DataParser<>(UniversityMapper.parseList(res));
        return parse.format(page, size);
    }

    public List<University> searchByUniversity(String name) {
        return universityRepository.findAllByNameContainsIgnoreCase(name);
    }

    public List<University> searchByCountry(String country) {
        Country countryRef = countryRepository.findCountryByNameIgnoreCase(country);
        return universityRepository.findAllByCountry(countryRef);
    }

    public List<University> searchByLanguage(String language) {
        Language languageRef = languageRepository.findLanguageByNameIgnoreCase(language);
        return universityRepository.findAll().stream()
                .filter(e -> e.getCountry().getLanguages().contains(languageRef))
                .toList();
    }

    @Transactional
    public List<University> createOrUpdate(List<University> university) {
        return universityRepository.saveAll(university);
    }
}
