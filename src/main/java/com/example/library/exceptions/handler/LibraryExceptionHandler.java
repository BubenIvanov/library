package com.example.library.exceptions.handler;

import com.example.library.dto.BookDto;
import com.example.library.exceptions.UnexistedIdException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LibraryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UnexistedIdException.class})
    protected ResponseEntity<Object> handleNotExistedId(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new BookDto(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
