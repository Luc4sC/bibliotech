package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserDTO(@NotEmpty(message = "email must be specified!") @Email String email,
                      @NotEmpty(message = "Full name must be specified!")
                      @Size(min = 7, message = "Full name must contain at least 7 characters")
                      String fullName,
                      @Past LocalDate birthdate,
                      @NotNull(message = "Address must be specified") AddressDTO addressDTO) {
}
