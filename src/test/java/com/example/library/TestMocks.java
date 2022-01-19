package com.example.library;

import com.example.library.dto.BookDto;
import com.example.library.dto.CreateBookDto;
import com.example.library.dto.SearchField;
import com.example.library.dto.SearchRequest;
import com.example.library.entity.Book;

import java.util.UUID;

public class TestMocks {

     Book bookWithId(Book book) {
        book.setId(UUID.randomUUID().toString());
        return book;
    }

     Book bookWithId() {
        Book book = new Book(mockCreateBookDto());
        return bookWithId(book);
    }

     BookDto bookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor("Author");
        bookDto.setGenre("Genre");
        bookDto.setName("Name");
        bookDto.setId(UUID.randomUUID().toString());
        return bookDto;
    }

     SearchRequest mockedSearchRequest(SearchField searchField) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setSearchField(searchField);
        searchRequest.setSearchValue("Joh");
        return searchRequest;
    }

     CreateBookDto mockCreateBookDto() {
        CreateBookDto createBookDto = new CreateBookDto();
        createBookDto.setAuthor("Author");
        createBookDto.setGenre("Genre");
        createBookDto.setName("Name");
        return createBookDto;
    }

}
