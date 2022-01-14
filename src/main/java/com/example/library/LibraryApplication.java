package com.example.library;

import com.example.library.dao.BooksRepository;
import com.example.library.entity.Book;
import com.example.library.jms.BooksSender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LibraryApplication implements CommandLineRunner {

    BooksRepository repository;
    BooksSender sender;

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Override
    public void run(String... args) {
        repository.deleteAll();
        mockedBooks().forEach(sender::send);
    }

    private List<Book> mockedBooks() {
        List<Book> books = new ArrayList<>();
        String johnTolkien = "John Tolkien";
        String fantacyGenre = "fantacy";
        books.add(new Book("The Hobbit", johnTolkien, fantacyGenre));
        books.add(new Book("The Lord of the Rings", johnTolkien, fantacyGenre));
        books.add(new Book("The Silmarillion", johnTolkien, fantacyGenre));
        books.add(new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", fantacyGenre));
        books.add(new Book("A Storm of Swords", "George R.R. Martin", fantacyGenre));
        books.add(new Book("The Art of Computer Programming", "Donald Knuth", "Non-fiction"));
        return books;
    }

}
