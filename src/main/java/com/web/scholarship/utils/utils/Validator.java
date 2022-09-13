package com.web.scholarship.utils.utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@AllArgsConstructor
@Component
public class Validator {
    /**
     * Return true if the length provided by the user is more than data
     * @param paginationSize
     * @param dataSize
     */
    public static boolean paginationSize(int paginationSize, int dataSize){
        return paginationSize > dataSize;
    }

    /**
     * Return true if the rest of the division between two number is equal to zero else false
     * @param dividend
     * @param divisor
     */
    public static boolean divisionRestZero (int dividend, int divisor){
        return dividend % divisor == 0;
    }

    /**
     * Return true if the length of the data is equal to zero else false
     * @param data
     * @param <T>
     */
    public static <T>  boolean dataLengthZero (List<T> data) {
        return data.size() == 0;
    }
}
