package com.web.scholarship.services;

import com.web.scholarship.models.Candidate;
import com.web.scholarship.models.StudyLevel;
import com.web.scholarship.models.dbUser.DBUser;
import com.web.scholarship.models.mapper.CandidateMapper;
import com.web.scholarship.models.mapper.WhoamiMapper;
import com.web.scholarship.models.mapper.models.Whoami;
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

import static java.lang.Integer.*;

@Service
@AllArgsConstructor
public class CandidateService {
    private CandidateRepository candidateRepository;
    private StudyLevelRepository studyLevelRepository;

    private DBUserRepository dbUserRepository;

    public DataFormat<com.web.scholarship.models.mapper.models.Candidate> getAll(int page, int size, Order order, CandidateSearchType orderBy) {
        DataParser<com.web.scholarship.models.mapper.models.Candidate> parse;
        String by = orderBy == CandidateSearchType.old ? "birthDate" : orderBy.toString();
        parse = new DataParser<>(CandidateMapper.parseList(candidateRepository.findAll(Sort.by(Sort.Direction.fromString(order.toString()), by))));
        return parse.format(page, size);
    }

    public Whoami auth(String username) {
        DBUser user = dbUserRepository.findDBUserByUsername(username);
        return WhoamiMapper.parse(candidateRepository.findCandidateByCredentialsId(user.getId()));
    }

    @Transactional
    public List<Candidate> createCandidate(List<Candidate> candidateList) {
        List<Candidate> candidates = candidateList.stream().peek(e -> {
                    Optional<DBUser> user = dbUserRepository.findById(e.getId());
                    user.ifPresent(e::setCredentials);
                }
        ).toList();
        return candidateRepository.saveAll(candidates);
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
        if (by == CandidateSearchType.old) {
            candidates = searchByOld(parseInt(searchValue));
        } else if (by == CandidateSearchType.firstName) {
            candidates = searchByFirstname(searchValue);
        } else if (by == CandidateSearchType.lastName) {
            candidates = searchByLastName(searchValue);
        } else if (by == CandidateSearchType.schoolOrigin) {
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
        return candidateRepository.findAll().stream().filter(e -> TimeCalc.yearFromDate(e.getBirthDate()) == year).toList();
    }

    public List<Candidate> searchByStudyLevel(String studyLevel) {
        StudyLevel studyLevelRef = studyLevelRepository.findStudyLevelByNameIgnoreCase(studyLevel);
        return candidateRepository.findAllByStudyLevel(studyLevelRef);
    }

    public List<Candidate> searchBySchoolOrigin(String schoolOrigin) {
        return candidateRepository.findAllBySchoolOriginContainsIgnoreCase(schoolOrigin);
    }
}
