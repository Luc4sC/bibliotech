package br.com.bibliotech.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDTO(@NotEmpty @Size(min = 7, max = 50) String street, @Max(value = 9999) int number,
                         @NotEmpty @Size(min = 5, max = 50) String neighborhood,
                         @NotEmpty @Size(min = 5, max = 25) String city,
                         @NotEmpty @Size(min = 5, max = 20) String state,
                         @NotEmpty @Pattern(regexp = "^\\d{5}-\\d{3}$") String cep) {
}
