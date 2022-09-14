package com.web.scholarship.models.mapper;

import com.web.scholarship.models.Candidate;
import com.web.scholarship.models.dbUser.DBRole;
import com.web.scholarship.models.mapper.models.Whoami;

public class WhoamiMapper {
    public static Whoami parse(Candidate candidate){
        return Whoami.builder()
                .candidate(CandidateMapper.parse(candidate))
                .roles(candidate.getCredentials().getDbRoles().stream().map(DBRole::getRole).toList())
                .build();
    }
}
