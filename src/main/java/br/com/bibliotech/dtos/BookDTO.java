package br.com.bibliotech.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record BookDTO(@NotEmpty @Size(min = 3, max = 50) String title , @NotEmpty @Size(min = 3, max = 50) String subtitle,
                      @NotEmpty @Size(min = 10, max = 50) String synopsis, @NotNull @PastOrPresent LocalDate publishDate,
                      @NotEmpty @Size(min = 10, max = 13) String isbn, @NotNull Long authorId,
                      @NotNull Long categoryId, @NotNull Long genreId, @NotNull Long publishCompanyId) {
}
