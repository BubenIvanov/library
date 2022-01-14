package com.example.library.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public enum SearchField {

    AUTHOR("author"),
    GENRE("genre");

    private String value;

    SearchField(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
