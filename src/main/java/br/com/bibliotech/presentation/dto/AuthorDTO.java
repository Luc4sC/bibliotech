package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AuthorDTO(@NotEmpty(message = "Author's full name must be specified")
                        @Size(min = 5, max = 50, message = "Author's full name must have more than 5 letters")
                        String fullName,
                        @NotEmpty(message = "Author's full name must be specified")
                        @Size(min = 2, max = 25, message = "Author's stage name must have more than 2 letters")
                        String stageName,
                        @NotNull(message = "Author's birthdate must be specified")
                        @Past(message = "Author's birthdate is invalid, try a past date")
                        LocalDate birthdate) {
}
