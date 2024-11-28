package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record BookDTO(@NotEmpty(message = "Book's isbn must be specified")
                      @Size(min = 10, max = 13, message = "Book's isbn must have at least 10 characters and a max of 10")
                      String isbn,
                      @NotEmpty(message = "Book's title must be specified") String title,
                      @NotNull(message = "Book's subtitle must be specified") String subtitle,
                      @NotEmpty(message = "Book's synopsis must be specified") String synopsis,
                      @Positive(message = "The number of pages must be greater than zero") int pages,
                      @PastOrPresent(message = "Book's publish date must be today or older") LocalDate publishDate,
                      @PositiveOrZero(message = "Book's quantity must be a positive number") int quantity,
                      @NotNull(message = "Author id must be specified") Long authorId,
                      @NotNull(message = "Category id must be specified") Long categoryId,
                      @NotNull(message = "Genre id must be specified") Long genreId,
                      @NotNull(message = "Publisher id must be specified") Long publisherId) {
}
