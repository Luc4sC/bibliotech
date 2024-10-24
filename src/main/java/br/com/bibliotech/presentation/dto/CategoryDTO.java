package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CategoryDTO(@NotEmpty(message = "Category name must be specified")
                          @Size(min = 5, max = 50, message = "Category's name must have more than 5 letters")
                          String name) {
}
