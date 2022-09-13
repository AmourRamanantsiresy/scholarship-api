package com.web.scholarship.models.mapper.models;

import com.web.scholarship.models.enums.Availability;
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
public class Application implements Serializable {
    private Availability status;
    private Scholarship scholarship;
    private List<Aspirant> candidates;
}
