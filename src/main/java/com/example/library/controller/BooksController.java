package com.example.library.controller;

import com.example.library.api.BookApi;
import com.example.library.dto.BookDto;
import com.example.library.dto.CreateBookDto;
import com.example.library.dto.SearchField;
import com.example.library.dto.SearchRequest;
import com.example.library.entity.Book;
import com.example.library.service.BooksServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BooksController implements BookApi {

    BooksServiceImpl booksService;

    @Override
    public BookDto createBook(final CreateBookDto bookDto) {
        return booksService.createBook(bookDto);
    }

    @Override
    public ResponseEntity<BookDto> getBook(final String bookId) {
        return ResponseEntity.ok(booksService.getBook(bookId));
    }

    @Override
    public ResponseEntity<BookDto> updateBook(final BookDto bookDto) {
        return ResponseEntity.ok(booksService.updateBook(bookDto));
    }

    @Override
    public void deleteBook(final String bookId) {
        booksService.deleteBook(bookId);
    }

    @Override
    public ResponseEntity<Page<Book>> getBooksBy(final SearchField searchField, final String searchValue, final Pageable pageable) {
        return ResponseEntity.ok(booksService.getBooksBy(new SearchRequest(searchField, searchValue), pageable));
    }

}
