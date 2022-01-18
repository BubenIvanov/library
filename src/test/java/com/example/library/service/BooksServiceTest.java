package com.example.library.service;

import com.example.library.dao.BooksRepository;
import com.example.library.dto.BookDto;
import com.example.library.dto.CreateBookDto;
import com.example.library.dto.SearchField;
import com.example.library.dto.SearchRequest;
import com.example.library.entity.Book;
import com.example.library.exceptions.UnexistedIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class BooksServiceTest {

    @Mock
    BooksRepository repository;

    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    BooksServiceImpl booksService;


    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBookSuccess() {
        when(repository.insert(any(Book.class))).thenReturn(bookWithId());
        BookDto bookDto = booksService.createBook(mockCreateBookDto());
        assertNotNull(bookDto.getId(), "[Assertion failed] - this argument is required; it must not be null");
    }

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

    private CreateBookDto mockCreateBookDto() {
        CreateBookDto createBookDto = new CreateBookDto();
        createBookDto.setAuthor("Author");
        createBookDto.setGenre("Genre");
        createBookDto.setName("Name");
        return createBookDto;
    }

    @Test
    void getBookSuccess() {
        when(repository.findById(anyString())).thenReturn(Optional.of(bookWithId()));
        BookDto bookDto = booksService.getBook("1234567890");
        assertNotNull(bookDto.getId(), "Book id must not be null");
    }

    @Test
    void getBookFailWithUnexistedId() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(UnexistedIdException.class, () ->
                booksService.getBook("1234567890")
        );
    }

    @Test
    void updateBookSuccess() {
        when(repository.existsById(anyString())).thenReturn(true);
        when(repository.save(any(Book.class))).thenReturn(bookWithId());
        BookDto bookDto = booksService.updateBook(bookDto());
        assertNotNull(bookDto.getId(), "Book id must not be null");
    }

    @Test
    void updateBookFailWithUnexistedId() {
        when(repository.existsById(anyString())).thenReturn(false);
        assertThrows(UnexistedIdException.class, () -> booksService.updateBook(bookDto()));
    }

    @Test
    void getBooksByGenreSuccess() {
        when(mongoTemplate.find(any(Query.class), any())).thenReturn(Collections.singletonList(bookWithId()));
        Page<Book> booksBy = booksService.getBooksBy(mockedSearchRequest(SearchField.GENRE), Pageable.unpaged());
        assertEquals(1, booksBy.getSize());
    }

    private SearchRequest mockedSearchRequest(SearchField searchField) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setSearchField(searchField);
        searchRequest.setSearchValue("Joh");
        return searchRequest;
    }

    @Test
    void getBooksByAuthorSuccess() {
        when(mongoTemplate.find(any(Query.class), any())).thenReturn(Collections.singletonList(bookWithId()));
        Page<Book> booksBy = booksService.getBooksBy(mockedSearchRequest(SearchField.AUTHOR), Pageable.unpaged());
        assertEquals(1, booksBy.getSize());
    }

}
