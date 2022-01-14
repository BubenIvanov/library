package com.example.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateBookDto {
    @Size()
    String name;
    String author;
    String genre;
}
