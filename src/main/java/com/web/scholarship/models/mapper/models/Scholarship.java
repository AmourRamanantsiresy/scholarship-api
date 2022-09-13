package com.web.scholarship.models.mapper.models;

import com.web.scholarship.models.enums.Availability;
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
public class Scholarship implements Serializable {
    private Long id;
    private String description;
    private Integer duration;
    private Integer amount;
    private Date registration_deadline;
    private Availability status;
    private String faculty;
    private String university;
    private String country;
    private List<String> languages;
}
