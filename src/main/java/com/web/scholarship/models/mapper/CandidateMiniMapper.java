package com.web.scholarship.models.mapper;

import com.web.scholarship.models.Candidate;
import com.web.scholarship.models.mapper.models.CandidateMini;

import java.util.List;

public class CandidateMiniMapper {
    public static CandidateMini parse(Candidate candidate){
        return CandidateMini.builder()
                .id(candidate.getId())
                .first_name(candidate.getFirstName())
                .last_name(candidate.getLastName())
                .build();
    }

    public static List<CandidateMini> parseList(List<Candidate> candidates){
        return candidates.stream().map(CandidateMiniMapper::parse).toList();
    }
}
