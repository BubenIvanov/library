package com.example.library.books.models;

import lombok.Getter;

@Getter
public enum Field {

    AUTHOR("author"),
    GENRE("genre");
    private String value;

    Field(String value) {
        this.value = value;
    }
}
