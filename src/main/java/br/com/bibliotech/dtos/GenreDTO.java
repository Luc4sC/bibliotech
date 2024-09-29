package br.com.bibliotech.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record GenreDTO(@NotEmpty @Size(min = 3, max = 50) String name) {
}
