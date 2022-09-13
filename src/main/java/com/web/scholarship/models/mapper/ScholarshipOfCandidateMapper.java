package com.web.scholarship.models.mapper;

import com.web.scholarship.models.Application;
import com.web.scholarship.models.mapper.models.ScholarshipOfCandidate;

import java.util.List;

public class ScholarshipOfCandidateMapper {
    public static ScholarshipOfCandidate parse(Application application){
        return ScholarshipOfCandidate.builder()
                .status(application.getStatus())
                .scholarship(ScholarshipMapper.parse(application.getScholarship()))
                .build();
    }

    public static List<ScholarshipOfCandidate> parseList(List<Application> applications){
        return applications.stream().map(ScholarshipOfCandidateMapper::parse).toList();
    }
}
