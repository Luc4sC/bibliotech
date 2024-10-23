package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.NotEmpty;

public record CategoryDTO(@NotEmpty(message = "Category name must be specified") String name) {
}
