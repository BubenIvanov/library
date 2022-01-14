package com.example.library.api;

import com.example.library.dto.BookDto;
import com.example.library.dto.CreateBookDto;
import com.example.library.dto.SearchField;
import com.example.library.entity.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "Library api",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/v1/books")
public interface BookApi {

    @ApiOperation("Create book")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created book dto", response = BookDto.class)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    BookDto createBook(final @RequestBody CreateBookDto bookDto);

    @ApiOperation("Get book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book dto", response = BookDto.class),
            @ApiResponse(code = 404, message = "Unexisted id")})
    @GetMapping(path = "/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BookDto> getBook(final @PathVariable String bookId);

    @ApiOperation("Update book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated book dto", response = BookDto.class),
            @ApiResponse(code = 404, message = "Unexisted id")})
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BookDto> updateBook(final @Valid @RequestBody BookDto bookDto);

    @ApiOperation("Delete book")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted"),
            @ApiResponse(code = 404, message = "Unexisted id")})
    @DeleteMapping(path = "/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBook(final @PathVariable String bookId);

    @ApiOperation("Search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book dto", response = BookDto.class)
    })
    @GetMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<Book>> getBooksBy(@RequestParam SearchField searchField, @RequestParam String searchValue, final Pageable pageable);

}

