package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.NotEmpty;

public record GenreDTO(@NotEmpty(message = "Genre's name must be specified") String name) {
}
