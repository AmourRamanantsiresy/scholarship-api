package com.web.scholarship.models.mapper.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candidate implements Serializable {
    private Long id;
    private String last_name;
    private String first_name;
    private String email;
    private String school_origin;
    private String about;
    private String phone_number;
    private String study_level;
    private String country;
    private List<String > languages;
    private Date birth_date;
}
