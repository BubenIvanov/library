package com.example.library;

import com.example.library.dao.BooksRepository;
import com.example.library.entity.Book;
import com.example.library.jms.BooksConsumer;
import com.example.library.jms.BooksSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = LibraryApplication.class)
@AutoConfigureMockMvc
@AutoConfigureDataMongo
class BooksApiIntegrationTests extends TestMocks {
    @Autowired
    private MockMvc mvc;

    @Autowired
    BooksRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    @MockBean
    BooksSender sender;
    @MockBean
    BooksConsumer consumer;

    @BeforeEach
    public void cleanUpDb() {
        repository.deleteAll();
    }

    @Test
    void createBookSuccess() throws Exception {
        mvc.perform(post("/v1/books")
                        .content(new ObjectMapper().writeValueAsString(mockCreateBookDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void getBookSuccess() throws Exception {
        String bookId = createBook().getId();
        mvc.perform(get("/v1/books/" + bookId)
                        .content(new ObjectMapper().writeValueAsString(mockCreateBookDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(bookId));
    }

    @Test
    void getBookFailWithUnexistedId() throws Exception {
        mvc.perform(get("/v1/books/123456")
                        .content(new ObjectMapper().writeValueAsString(mockCreateBookDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Unexisted id!"));
    }

    @Test
    void updateBookSuccess() throws Exception {
        Book book = createBook();
        String updatedName = "Updated name";
        book.setName(updatedName);
        mvc.perform(put("/v1/books")
                        .content(new ObjectMapper().writeValueAsString(book.toDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value(updatedName));

    }

    @Test
    void updateBookFailWithUnexistedId() throws Exception {
        mvc.perform(put("/v1/books")
                        .content(new ObjectMapper().writeValueAsString(bookWithId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Unexisted id!"));

    }

    @Test
    void getBooksByGenreSuccess() throws Exception {
        Book book = createBook();
        MvcResult mvcResult = mvc.perform(get("/v1/books/search")
                        .queryParam("searchField", "genre")
                        .queryParam("searchValue", "fantacy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content[0].id").value(book.getId()))
                .andExpect(jsonPath("$.content[0].genre").value(book.getGenre()))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getBooksByAuthorSuccess() throws Exception {
        Book book = createBook();
        mvc.perform(get("/v1/books/search")
                        .queryParam("searchField", "author")
                        .queryParam("searchValue", "J.K")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content[0].id").value(book.getId()))
                .andExpect(jsonPath("$.content[0].genre").value(book.getGenre()))
                .andReturn();
    }

    Book createBook() {
        return repository.save(new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", "fantacy"));
    }
}
