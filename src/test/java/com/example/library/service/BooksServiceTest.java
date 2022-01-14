package com.example.library.service;

import com.example.library.dao.BooksRepository;
import com.example.library.dto.BookDto;
import com.example.library.dto.CreateBookDto;
import com.example.library.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BooksServiceTest {

    @Mock
    BooksRepository repository;

    @InjectMocks
    BooksServiceImpl booksService;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBookSuccess() {
        CreateBookDto createBookDto = mockCreateBookDto();
        Book book = new Book(createBookDto);

        when(repository.insert(any(Book.class))).thenReturn(bookWithId(book));

        BookDto bookDto = booksService.createBook(createBookDto);
        Assert.notNull(bookDto.getId(), "[Assertion failed] - this argument is required; it must not be null");
    }

    Book bookWithId(Book book) {
        book.setId(UUID.randomUUID().toString());
        return book;
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

    }

    @Test
    void getBookFailWithUnexistedId() {

    }

    @Test
    void updateBookSuccess() {

    }

    @Test
    void updateBookFailWithUnexistedId() {

    }

    @Test
    void deleteBookSuccess() {

    }

    @Test
    void getBooksByGenreSuccess() {

    }

    @Test
    void getBooksByAuthorSuccess() {

    }


}
