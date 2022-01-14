package com.example.library.exceptions;

public class UnexistedIdException extends RuntimeException {
    public UnexistedIdException(String message) {
        super(message);
    }
}
