package br.com.bibliotech.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorDTO(@NotEmpty @Size(min = 5, max = 50) String fullName,
                        @NotEmpty @Size(min = 5, max = 25) String stageName, @NotNull LocalDate birthdate) {
}
