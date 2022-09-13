package com.web.scholarship.models.mapper.models;

import com.web.scholarship.models.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aspirant implements Serializable {
    private CandidateMini candidate;
    private Date applying_date;
    private ApplicationStatus status;
}

