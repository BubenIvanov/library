package com.example.library.dto;

import com.example.library.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto extends CreateBookDto {

    @NotNull
    String id;
    String message;

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.genre = book.getGenre();
    }

    public BookDto(String message) {
        this.message = message;
    }
}
