package com.example.library.books.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Document
public class Book {

    @Id
    String id;
    String name;
    String author;
    String genre;

    public Book(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(CreateBookDto bookDto) {
        this.name = bookDto.getName();
        this.author = bookDto.getAuthor();
        this.genre = bookDto.getGenre();
    }

    public Book(BookDto bookDto) {
        this.name = bookDto.getId();
        this.name = bookDto.getName();
        this.author = bookDto.getAuthor();
        this.genre = bookDto.getGenre();
    }

    public BookDto toDto() {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor(author);
        bookDto.setGenre(genre);
        bookDto.setId(id);
        bookDto.setName(name);
        return bookDto;
    }
}

