package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record GenreDTO(@NotEmpty(message = "Genre's name must be specified")
                       @Size(min = 5, max = 50, message = "Genre's name must have more than 5 letters")
                       String name) {
}
