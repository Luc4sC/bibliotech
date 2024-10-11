package br.com.bibliotech.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CopyDTO(@NotNull @Max(value = 1000) int numeration, @NotEmpty String isbn, @NotNull Long bookId) {
}
