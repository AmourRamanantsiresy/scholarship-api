package com.web.scholarship.services;

import com.web.scholarship.models.Country;
import com.web.scholarship.models.Language;
import com.web.scholarship.models.Scholarship;
import com.web.scholarship.models.enums.ScholarshipSearchType;
import com.web.scholarship.models.mapper.ScholarshipMapper;
import com.web.scholarship.repository.CountryRepository;
import com.web.scholarship.repository.LanguageRepository;
import com.web.scholarship.repository.ScholarshipRepository;
import com.web.scholarship.utils.utils.Order;
import com.web.scholarship.utils.pagination.DataFormat;
import com.web.scholarship.utils.pagination.DataParser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScholarshipService {
    private ScholarshipRepository scholarshipRepository;
    private CountryRepository countryRepository;
    private LanguageRepository languageRepository;

    public com.web.scholarship.models.mapper.models.Scholarship getOne(Long id) {
        Optional<Scholarship> scholarship = scholarshipRepository.findById(id);
        if (scholarship.isPresent()) {
            return ScholarshipMapper.parse(scholarship.get());
        }
        throw new RuntimeException("Bad request");
    }

    public DataFormat<com.web.scholarship.models.mapper.models.Scholarship> getAll(int page, int size, Order order, ScholarshipSearchType by) {
        String orderBy = by == ScholarshipSearchType.deadline ?
                "registration_deadline" :
                (by == ScholarshipSearchType.language) ?
                        "university" :
                        by == ScholarshipSearchType.country ? "university" : by.toString();
        DataParser<com.web.scholarship.models.mapper.models.Scholarship> parse = new DataParser<>(ScholarshipMapper.parseList(scholarshipRepository.findAll(Sort.by(Sort.Direction.fromString(order.toString()), orderBy))));
        return parse.format(page, size);
    }

    public DataFormat<com.web.scholarship.models.mapper.models.Scholarship> searchScholarship(ScholarshipSearchType type, String name, int page, int size) {
        List<Scholarship> res;
        if (type == ScholarshipSearchType.faculty) {
            res = scholarshipRepository.findAllByFaculty_Name(name);
        } else if (type == ScholarshipSearchType.university) {
            res = scholarshipRepository.findAllByUniversity_Name(name);
        } else if (type == ScholarshipSearchType.country) {
            res = searchByCountry(name);
        } else if (type == ScholarshipSearchType.language) {
            res = searchByLanguage(name);
        } else {
            throw new RuntimeException("Bad request");
        }
        DataParser<com.web.scholarship.models.mapper.models.Scholarship> parse = new DataParser<>(ScholarshipMapper.parseList(res));
        return parse.format(page, size);
    }

    public DataFormat<com.web.scholarship.models.mapper.models.Scholarship> searchScholarshipByCreation(Date beginning, Date ending, int page, int size) {
        List<com.web.scholarship.models.mapper.models.Scholarship> res = ScholarshipMapper.parseList(scholarshipRepository.findAllByCreationDateBetween(beginning, ending));
        DataParser<com.web.scholarship.models.mapper.models.Scholarship> parse = new DataParser<>(res);
        return parse.format(page, size);
    }

    public DataFormat<com.web.scholarship.models.mapper.models.Scholarship> searchScholarshipByDuration(int duration1, int duration2, int page, int size) {
        List<com.web.scholarship.models.mapper.models.Scholarship> res = ScholarshipMapper.parseList(scholarshipRepository.findAllByDurationIsBetween(duration1, duration2));
        DataParser<com.web.scholarship.models.mapper.models.Scholarship> parse = new DataParser<>(res);
        return parse.format(page, size);
    }

    public DataFormat<com.web.scholarship.models.mapper.models.Scholarship> searchScholarshipByAmount(int value, int page, int size) {
        List<com.web.scholarship.models.mapper.models.Scholarship> res = ScholarshipMapper.parseList(scholarshipRepository.findAllByAmount_Amount(value));
        DataParser<com.web.scholarship.models.mapper.models.Scholarship> parse = new DataParser<>(res);
        return parse.format(page, size);
    }

    public List<Scholarship> searchByCountry(String country) {
        Country countryRef = countryRepository.findCountryByNameIgnoreCase(country);
        return scholarshipRepository.findAllByUniversity_Country(countryRef);
    }

    public List<Scholarship> searchByLanguage(String language) {
        Language languageRef = languageRepository.findLanguageByNameIgnoreCase(language);
        List<Scholarship> scholarshipRef = scholarshipRepository.findAll();
        return scholarshipRef.stream().filter(e -> e.getUniversity().getCountry().getLanguages().contains(languageRef)).toList();
    }

    @Transactional
    public List<Scholarship> createOrUpdate(List<Scholarship> scholarships) {
        return scholarshipRepository.saveAll(scholarships);
    }
}
