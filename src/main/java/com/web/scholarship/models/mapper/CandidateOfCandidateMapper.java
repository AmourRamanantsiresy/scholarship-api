package com.web.scholarship.models.mapper;

import com.web.scholarship.models.Application;
import com.web.scholarship.models.mapper.models.CandidateOfCandidate;

import java.util.List;

public class CandidateOfCandidateMapper {
    public static CandidateOfCandidate parse(Application application) {
        return CandidateOfCandidate.builder().candidate(CandidateMiniMapper.parse(application.getCandidate())).status(application.getStatus()).build();
    }

    public static List<CandidateOfCandidate> parseList(List<Application> applications) {
        return applications.stream().map(CandidateOfCandidateMapper::parse).toList();
    }
}
