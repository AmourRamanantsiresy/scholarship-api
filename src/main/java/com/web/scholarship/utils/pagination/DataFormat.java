package com.web.scholarship.utils.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class DataFormat<T> {
    private int lastPage;
    private List<T> data;
}
