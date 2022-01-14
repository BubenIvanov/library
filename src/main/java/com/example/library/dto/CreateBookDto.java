package com.example.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBookDto {
    String name;
    String author;
    String genre;
}
