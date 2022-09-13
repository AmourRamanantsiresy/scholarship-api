package com.web.scholarship.services;

import com.web.scholarship.models.Candidate;
import com.web.scholarship.models.StudyLevel;
import com.web.scholarship.models.dbUser.DBUser;
import com.web.scholarship.models.mapper.CandidateMapper;
import com.web.scholarship.repository.CandidateRepository;
import com.web.scholarship.repository.StudyLevelRepository;
import com.web.scholarship.models.enums.CandidateSearchType;
import com.web.scholarship.repository.dbUser.DBUserRepository;
import com.web.scholarship.utils.utils.Order;
import com.web.scholarship.utils.utils.TimeCalc;
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
public class CandidateService {
    private CandidateRepository candidateRepository;
    private StudyLevelRepository studyLevelRepository;

    private DBUserRepository dbUserRepository;

    public DataFormat<com.web.scholarship.models.mapper.models.Candidate> getAll(int page, int size, Order order, CandidateSearchType orderBy) {
        DataParser<com.web.scholarship.models.mapper.models.Candidate> parse;
        String by = orderBy == CandidateSearchType.OLD ? "birth_date" : order.toString();
        parse = new DataParser<>(CandidateMapper.parseList(candidateRepository.findAll(Sort.by(Sort.Direction.fromString(order.toString()), by))));
        return parse.format(page, size);
    }

    public Candidate auth(String username) {
        DBUser user = dbUserRepository.findDBUserByUsername(username);
        return candidateRepository.findCandidateByCredentials(user);
    }

    @Transactional
    public List<com.web.scholarship.models.mapper.models.Candidate> createCandidate(List<Candidate> candidate) {
        return CandidateMapper.parseList(candidateRepository.saveAll(candidate));
    }

    public com.web.scholarship.models.mapper.models.Candidate getOne(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            return CandidateMapper.parse(candidate.get());
        }
        throw new RuntimeException("Resource not found");
    }

    public DataFormat<com.web.scholarship.models.mapper.models.Candidate> searchBy(String searchValue, CandidateSearchType by, int page, int size) {
        DataParser<com.web.scholarship.models.mapper.models.Candidate> parse;
        List<Candidate> candidates;
        if (by == CandidateSearchType.OLD) {
            candidates = searchByOld(Integer.valueOf(searchValue));
        } else if (by == CandidateSearchType.FIRST_NAME) {
            candidates = searchByFirstname(searchValue);
        } else if (by == CandidateSearchType.LAST_NAME) {
            candidates = searchByLastName(searchValue);
        } else if (by == CandidateSearchType.SCHOOL_ORIGIN) {
            candidates = searchBySchoolOrigin(searchValue);
        } else {
            candidates = searchByStudyLevel(searchValue);
        }
        parse = new DataParser<>(CandidateMapper.parseList(candidates));
        return parse.format(page, size);
    }

    public List<Candidate> searchByFirstname(String name) {
        return candidateRepository.findAllByFirstNameContainsIgnoreCase(name);
    }

    public List<Candidate> searchByLastName(String name) {
        return candidateRepository.findAllByLastNameIsLikeIgnoreCase(name);
    }

    public List<Candidate> searchByOld(int old) {
        int year = TimeCalc.yearFromOld(old);
        return candidateRepository
                .findAll()
                .stream()
                .filter(e -> TimeCalc.yearFromDate(e.getBirthDate()) == year)
                .toList();
    }

    public List<Candidate> searchByStudyLevel(String studyLevel) {
        StudyLevel studyLevelRef = studyLevelRepository.findStudyLevelByNameIgnoreCase(studyLevel);
        return candidateRepository.findAllByStudyLevel(studyLevelRef);
    }

    public List<Candidate> searchBySchoolOrigin(String schoolOrigin) {
        return candidateRepository.findAllBySchoolOriginContainsIgnoreCase(schoolOrigin);
    }
}
