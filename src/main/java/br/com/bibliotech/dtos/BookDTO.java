package br.com.bibliotech.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record BookDTO(@NotEmpty @Size(min = 3, max = 50) String title , @NotEmpty @Size(min = 3, max = 50) String subtitle,
                      @NotEmpty @Size(min = 10, max = 50) String synopsis, @NotNull int pages,
                      @NotNull @PastOrPresent LocalDate publishDate, @NotNull Long authorId,
                      @NotNull Long categoryId, @NotNull Long genreId, @NotNull Long publishCompanyId) {
}
