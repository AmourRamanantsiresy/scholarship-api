package com.web.scholarship.utils.pagination;


import com.web.scholarship.utils.utils.Validator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
public class DataParser<T> {
    private List<T> data;

    public DataFormat<T> format(int page, int size) {
        page = page - 1;
        int length = data.size();
        int paginationTempLength = length / size;
        int pageStart = page * size;
        int pageEnd = (page * size) + size;

        if (Validator.dataLengthZero(data)) {
            return new DataFormat<>(0, null);
        } else if (page == -1 || Validator.paginationSize(size, length)) {
            return new DataFormat<T>(1, data);
        } else if (Validator.divisionRestZero(length, size)) {
            List<T> result = data.subList(pageStart, pageEnd);
            return new DataFormat<T>(paginationTempLength, result);
        } else if (page < paginationTempLength) {
            List<T> result = data.subList(pageStart, pageEnd);
            return new DataFormat<T>(paginationTempLength + 1, result);
        } else {
            List<T> result = data.subList(pageStart, length);
            return new DataFormat<T>(paginationTempLength + 1, result);
        }
    }
}
