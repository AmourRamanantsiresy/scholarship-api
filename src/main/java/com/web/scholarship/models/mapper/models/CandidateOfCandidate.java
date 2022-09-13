package com.web.scholarship.models.mapper.models;

import com.web.scholarship.models.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateOfCandidate implements Serializable {
    private CandidateMini candidate;
    private ApplicationStatus status;
}
