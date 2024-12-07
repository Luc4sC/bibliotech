package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestDTO(@NotNull Long userId, @NotNull @NotEmpty List<Long> booksIds) {
}
