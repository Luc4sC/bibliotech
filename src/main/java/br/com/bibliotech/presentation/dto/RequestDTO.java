package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestDTO(@NotNull Long userId, @NotNull @NotBlank List<Long> booksIds) {
}
