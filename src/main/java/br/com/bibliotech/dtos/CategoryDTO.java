package br.com.bibliotech.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CategoryDTO(@NotEmpty @Size(min = 3, max = 50) String name) {
}
