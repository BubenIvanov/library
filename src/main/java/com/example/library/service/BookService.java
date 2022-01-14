package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.dto.BookDto;
import com.example.library.dto.CreateBookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.library.dto.SearchRequest;

public interface BookService {

    BookDto createBook(final CreateBookDto bookDto);

    BookDto getBook(final String id);

    BookDto updateBook(final BookDto book);

    void deleteBook(final String id);

    Page<Book> getBooksBy(final SearchRequest request,final Pageable pageable);

}
