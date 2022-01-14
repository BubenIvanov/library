package com.example.library.entity;

import com.example.library.dto.BookDto;
import com.example.library.dto.CreateBookDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Document
public class Book implements Serializable {

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
        this.id = bookDto.getId();
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

