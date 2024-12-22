package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LoanRequestDTO(@NotEmpty String userEmail, @NotNull @NotEmpty List<Long> booksIds) {
}
