package com.web.scholarship.models.mapper.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class University implements Serializable {
    private Long id;
    private String name;
    private String country;
    private List<String > languages;
}
