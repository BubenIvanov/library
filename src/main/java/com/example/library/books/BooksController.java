package com.example.library.books;

import com.example.library.books.models.Book;
import com.example.library.books.models.BookDto;
import com.example.library.books.models.CreateBookDto;
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

    @GetMapping(path = "/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> getBook(final @PathVariable String bookId) {
        try {
            return ResponseEntity.ok(booksService.getBook(bookId));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BookDto(ex.getMessage()));
        }
    }

    //todo: id to path variable?
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> updateBook(final @RequestBody BookDto bookDto) {
        try {
            return ResponseEntity.ok(booksService.updateBook(bookDto));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BookDto(ex.getMessage()));
        }
    }

    //question request object vs request params?
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBooksBy(final @RequestParam String searchField,
                                        final @RequestParam String searchValue,
                                        final Pageable pageable) {
//        try {
            return ResponseEntity.ok(booksService.getBooksBy(searchField,searchValue, pageable));
//        } catch (IllegalArgumentException ex) {
//            return ResponseEntity.badRequest().body(new BookDto(ex.getMessage()));
//        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(final @RequestBody CreateBookDto bookDto) {
        return booksService.createBook(bookDto);
    }

    @DeleteMapping(path = "/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String bookId) {
        booksService.deleteBook(bookId);
    }

}
