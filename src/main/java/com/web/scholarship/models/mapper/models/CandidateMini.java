package com.web.scholarship.models.mapper.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateMini implements Serializable {
    private Long id;
    private String first_name;
    private String last_name;
}
