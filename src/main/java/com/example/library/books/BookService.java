package com.example.library.books;

import com.example.library.books.models.Book;
import com.example.library.books.models.BookDto;
import com.example.library.books.models.CreateBookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookDto updateBook(BookDto book);

    BookDto getBook(String id);

    void deleteBook(String id);

    Page<Book> getBooksBy(String searchField, String searchValue, Pageable pageable);

    BookDto createBook(CreateBookDto bookDto);
}
