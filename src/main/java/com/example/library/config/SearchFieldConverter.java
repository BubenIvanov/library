package com.example.library.config;

import com.example.library.dto.SearchField;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SearchFieldConverter implements Converter<String, SearchField> {

    @Override
    public SearchField convert(String value) {
        return SearchField.valueOf(value.toUpperCase());
    }
}