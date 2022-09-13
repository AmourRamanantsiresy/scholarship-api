package com.web.scholarship.models.mapper;

import com.web.scholarship.models.Language;
import com.web.scholarship.models.University;

import java.util.List;

public class UniversityMapper {
    public static com.web.scholarship.models.mapper.models.University parse(University university){
        return com.web.scholarship.models.mapper.models.University.builder()
                .id(university.getId())
                .country(university.getCountry().getName())
                .name(university.getName())
                .languages(university.getCountry().getLanguages().stream().map(Language::getName).toList())
                .build();
    }

    public static List<com.web.scholarship.models.mapper.models.University> parseList(List<University> universityList){
        return universityList.stream().map(UniversityMapper::parse).toList();
    }
}
