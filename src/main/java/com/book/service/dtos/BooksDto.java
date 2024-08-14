package com.book.service.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BooksDto {
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 50, message = "Title should be 3 to 50")
    private String title;
    @NotBlank(message = "Author cannot be blank")
    @Size(min = 3, max = 50, message = "Author should be 3 to 50")
    private String author;
    @NotBlank(message = "Year cannot be blank")
    private int year;
}
