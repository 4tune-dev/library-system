package com.tune.library.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotBlank(message = "Publisher is required")
    private String publisher;

    @Min(value = 1000, message = "Year must be valid")
    private int year;

    @Min(value = 1, message = "At least one copy must be available")
    private int copiesAvailable;

    @NotBlank(message = "Author is required")
    private String author;
}