package com.web.scholarship.models.mapper;

import com.web.scholarship.models.Language;
import com.web.scholarship.models.Scholarship;

import java.util.List;

public class ScholarshipMapper {
    public static com.web.scholarship.models.mapper.models.Scholarship parse(Scholarship scholarship) {
        return com.web.scholarship.models.mapper.models.Scholarship.builder()
                .id(scholarship.getId())
                .description(scholarship.getDescription())
                .duration(scholarship.getDuration())
                .university(scholarship.getUniversity().getName())
                .faculty(scholarship.getFaculty().getName())
                .amount(scholarship.getAmount().getAmount())
                .registration_deadline(scholarship.getRegistrationDeadline())
                .country(scholarship.getUniversity().getCountry().getName())
                .languages(scholarship.getUniversity().getCountry().getLanguages().stream().map(Language::getName).toList())
                .status(scholarship.getStatus())
                .build();
    }

    public static List<com.web.scholarship.models.mapper.models.Scholarship> parseList(List<Scholarship> scholarships) {
        return scholarships.stream().map(ScholarshipMapper::parse).toList();
    }
}
