package com.web.scholarship.models.mapper;

import com.web.scholarship.models.Candidate;
import com.web.scholarship.models.Language;

import java.util.List;

public class CandidateMapper {
    public static com.web.scholarship.models.mapper.models.Candidate parse(Candidate candidate){
        return com.web.scholarship.models.mapper.models.Candidate.builder()
                .id(candidate.getId())
                .country(candidate.getCountry().getName())
                .about(candidate.getAbout())
                .birth_date(candidate.getBirthDate())
                .email(candidate.getEmail())
                .first_name(candidate.getFirstName())
                .last_name(candidate.getLastName())
                .phone_number(candidate.getPhoneNumber())
                .study_level(candidate.getStudyLevel().getName())
                .school_origin(candidate.getSchoolOrigin())
                .languages(candidate.getCountry().getLanguages().stream().map(Language::getName).toList())
                .build();
    }

    public static List<com.web.scholarship.models.mapper.models.Candidate> parseList(List<Candidate> candidates){
        return candidates.stream().map(CandidateMapper::parse).toList();
    }
}
