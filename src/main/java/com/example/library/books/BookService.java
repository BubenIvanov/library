package com.example.library.books;

import com.example.library.books.models.Book;
import com.example.library.books.models.BookDto;
import com.example.library.books.models.CreateBookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.library.books.models.SearchRequest;

public interface BookService {

    BookDto createBook(final CreateBookDto bookDto);

    BookDto getBook(final String id);

    BookDto updateBook(final BookDto book);

    void deleteBook(final String id);

    Page<Book> getBooksBy(final SearchRequest request,final Pageable pageable);

}
