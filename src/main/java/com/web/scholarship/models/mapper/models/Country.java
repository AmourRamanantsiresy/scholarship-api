package com.web.scholarship.models.mapper.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country implements Serializable {
    private Long id;
    private String name;
    private List<String> languages;
}
