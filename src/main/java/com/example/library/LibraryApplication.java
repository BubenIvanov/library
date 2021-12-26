package com.example.library;

import com.example.library.books.BooksRepository;
import com.example.library.books.models.Book;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@EnableJms
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LibraryApplication implements CommandLineRunner {

    BooksRepository repository;
    JmsTemplate jmsTemplate;

    public static void main(String[] args) {
       SpringApplication.run(LibraryApplication.class, args);
    }

    @Override
    public void run(String... args) {
        repository.deleteAll();
        repository.saveAll(mockedBooks());

        log.info("Sending an a book message");
        jmsTemplate.convertAndSend("library", new Book("Dune", "Frank", "Science fiction"));
    }

    private List<Book> mockedBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Hobbit", "John Tolkien", "fantacy"));
        books.add(new Book("The Lord of the Rings", "John Tolkien", "fantacy"));
        books.add(new Book("The Silmarillion", "John Tolkien", "fantacy"));
        books.add(new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", "fantacy"));
        books.add(new Book("A Storm of Swords", "George R.R. Martin", "fantacy"));
        books.add(new Book("The Art of Computer Programming", "Donald Knuth", "Non-fiction"));
        return books;
    }
}
