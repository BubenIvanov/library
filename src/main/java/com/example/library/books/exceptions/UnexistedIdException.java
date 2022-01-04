package com.example.library.books.exceptions;

public class UnexistedIdException extends RuntimeException {
    public UnexistedIdException(String message) {
        super(message);
    }
}
