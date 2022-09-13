package com.web.scholarship.utils;

import com.web.scholarship.utils.pagination.DataFormat;
import com.web.scholarship.utils.pagination.DataParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataParserTest {
    private static final List<Integer> dataForTest = new ArrayList<>();
    private static DataParser<Integer> dataParser;

    @BeforeAll
    static void makeData(){
        for(int i = 0; i< 10; i++){
            dataForTest.add(i);
        }
        dataParser = new DataParser<>(dataForTest);
    }

    @Test
    void dataLengthZero (){
        List<Integer> dataLengthZero = new ArrayList<>();
        DataParser<Integer> parser = new DataParser<>(dataLengthZero);

        DataFormat<Integer> dataResult = parser.format(1, 10);

        DataFormat<Integer> expectedData = new DataFormat<>(0, null);

        assertEquals(dataResult, expectedData);
    }

    @Test
    void dataLengthLessThanPaginationSize (){
        DataFormat<Integer> expected = new DataFormat<>(1, dataForTest);

        DataFormat<Integer> dataResult = dataParser.format(1, 20);

        assertEquals(expected, dataResult);
    }

    @Test
    void divisionRestZero(){
        DataFormat<Integer> expected = new DataFormat<>(2, dataForTest.subList(5, 10));

        DataFormat<Integer> dataResult = dataParser.format(2, 5);

        assertEquals(expected, dataResult);
    }

    @Test
    void divisionRestNotZeroPageLessThanDataLength(){
        DataFormat<Integer> expected = new DataFormat<>(4, dataForTest.subList(3, 6));

        DataFormat<Integer> dataResult = dataParser.format(2, 3);

        assertEquals(expected, dataResult);
    }

    @Test
    void divisionRestNotZeroPageMoreThanDataLength(){
        DataFormat<Integer> expected = new DataFormat<>(4, dataForTest.subList(9, 10));

        DataFormat<Integer> dataResult = dataParser.format(4, 3);

        assertEquals(expected, dataResult);
    }
}
