package com.example.library.books.exceptions;

import com.example.library.books.models.BookDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UnexistedIdException.class})
    protected ResponseEntity<Object> handleNotExistedId(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new BookDto(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    @ExceptionHandler(value = {InvalidFormatException.class})
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new BookDto("Unexisted search field!"), HttpStatus.BAD_REQUEST);
    }

}
