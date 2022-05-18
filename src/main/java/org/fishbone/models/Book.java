package org.fishbone.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;

    @NotEmpty(message = "Book title must be not empty")
    @Size(min = 2, max = 100, message = "Book title must be between 2 and 100 characters")
    private String title;

    @NotEmpty(message = "Author name must be not empty")
    @Size(min = 2, max = 100, message = "Author name must be between 2 and 100 characters")
    private String author;

    @Min(value = 1900, message = "Book year must be greater than 1900")
    private int year;
}
