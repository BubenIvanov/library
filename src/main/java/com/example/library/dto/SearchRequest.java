package com.example.library.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    @NotNull(message = "Search field must not be null!")
    SearchField searchField;

    @NotNull(message = "Search value must be present!")
    String searchValue;
}

