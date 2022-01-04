package com.example.library.books;

import com.example.library.books.models.Book;
import com.example.library.books.models.BookDto;
import com.example.library.books.models.CreateBookDto;
import com.example.library.books.models.SearchRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//todo: interface?
@RequestMapping("/v1/books")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BooksController {

    BooksServiceImpl booksService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(final @RequestBody CreateBookDto bookDto) {
        return booksService.createBook(bookDto);
    }

    @GetMapping(path = "/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> getBook(final @PathVariable String bookId) {
        return ResponseEntity.ok(booksService.getBook(bookId));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> updateBook(final @RequestBody BookDto bookDto) {
        return ResponseEntity.ok(booksService.updateBook(bookDto));
    }

    @DeleteMapping(path = "/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String bookId) {
        booksService.deleteBook(bookId);
    }

    @GetMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Book>> getBooksBy(final @RequestBody SearchRequest request, final Pageable pageable) {
        return ResponseEntity.ok(booksService.getBooksBy(request, pageable));
    }

}
