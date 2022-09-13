package com.web.scholarship.models.mapper;

import com.web.scholarship.models.Country;
import com.web.scholarship.models.Language;
import java.util.List;

public class CountryMapper {
    public static com.web.scholarship.models.mapper.models.Country parse(Country country){
        return com.web.scholarship.models.mapper.models.Country.builder()
                .id(country.getId())
                .name(country.getName())
                .languages(country.getLanguages().stream().map(Language::getName).toList())
                .build();
    }

    public static List<com.web.scholarship.models.mapper.models.Country> parseList(List<Country> countries){
        return countries.stream().map(CountryMapper::parse).toList();
    }
}
