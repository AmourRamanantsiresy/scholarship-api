package com.web.scholarship.services;

import com.web.scholarship.models.Application;
import com.web.scholarship.models.enums.ApplicationStatus;
import com.web.scholarship.models.enums.Availability;
import com.web.scholarship.models.Candidate;
import com.web.scholarship.models.mapper.CandidateOfCandidateMapper;
import com.web.scholarship.models.mapper.ScholarshipOfCandidateMapper;
import com.web.scholarship.models.mapper.models.CandidateOfCandidate;
import com.web.scholarship.models.mapper.models.ScholarshipOfCandidate;
import com.web.scholarship.repository.ApplicationRepository;
import com.web.scholarship.repository.CandidateRepository;
import com.web.scholarship.repository.ScholarshipRepository;
import com.web.scholarship.utils.pagination.DataFormat;
import com.web.scholarship.utils.pagination.DataParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationService {
    private ApplicationRepository applicationRepository;
    private ScholarshipRepository scholarshipRepository;
    private CandidateRepository candidateRepository;

    public DataFormat<ScholarshipOfCandidate> getAllScholarshipForCandidate(Long id, int page, int size) {
        DataParser<ScholarshipOfCandidate> scholarshipParsed;
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            List<com.web.scholarship.models.Application> res = applicationRepository.findAllByCandidate(candidate.get());
            scholarshipParsed = new DataParser<>(ScholarshipOfCandidateMapper.parseList(res));
            return scholarshipParsed.format(page, size);
        }
        throw new RuntimeException("Resource not found");
    }

    public  DataFormat<CandidateOfCandidate> getAllCandidateForScholarship(Long id, int page, int size) {
        DataParser<CandidateOfCandidate> candidateParsed;
        Optional<com.web.scholarship.models.Scholarship> scholarship = scholarshipRepository.findById(id);
        if (scholarship.isPresent()) {
            List<com.web.scholarship.models.Application> res = applicationRepository.findAllByScholarship(scholarship.get());
            candidateParsed = new DataParser<>(CandidateOfCandidateMapper.parseList(res));
            return candidateParsed.format(page, size);
        }
        throw new RuntimeException("Resource not found");
    }

    public String close(com.web.scholarship.models.Scholarship scholarship){
        Optional<com.web.scholarship.models.Scholarship> res = scholarshipRepository.findById(scholarship.getId());
        if(res.isPresent() && res.get().getStatus() != Availability.UNAVAILABLE){
            com.web.scholarship.models.Scholarship temp = res.get();
            temp.setStatus(Availability.UNAVAILABLE);
            scholarshipRepository.save(temp);
            return "Application closed with success";
        }
        throw new RuntimeException("Resource not found");
    }

    public String apply(com.web.scholarship.models.Scholarship scholarship,Candidate candidate){
        Optional<com.web.scholarship.models.Scholarship> res1 = scholarshipRepository.findById(scholarship.getId());
        Optional<com.web.scholarship.models.Candidate> res2 = candidateRepository.findById(candidate.getId());
        if(res1.isPresent() && res2.isPresent()){
            Optional<Application> res3 = applicationRepository.findApplicationByScholarshipAndCandidate(res1.get(), res2.get());
            if(res3.isEmpty()){
                Application newApplication = Application.builder()
                        .scholarship(res1.get())
                        .candidate(res2.get())
                        .build();

                applicationRepository.save(newApplication);
                return "Application success";
            }
        }
        throw new RuntimeException("Bad request");
    }

    public String acceptApplication(com.web.scholarship.models.Scholarship scholarship, Candidate candidate){
        Optional<com.web.scholarship.models.Scholarship> res1 = scholarshipRepository.findById(scholarship.getId());
        Optional<com.web.scholarship.models.Candidate> res2 = candidateRepository.findById(candidate.getId());
        if(res1.isPresent() && res2.isPresent()){
            Optional<Application> res3 = applicationRepository.findApplicationByScholarshipAndCandidate(res1.get(), res2.get());
            if(res3.isPresent()){
                Application temp = res3.get();
                temp.setStatus(ApplicationStatus.ACCEPTED);
                applicationRepository.save(temp);
                return "Application accepted with success";
            }
        }
        throw new RuntimeException("Resource not found");
    }
}
