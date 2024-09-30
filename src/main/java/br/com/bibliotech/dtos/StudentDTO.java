package br.com.bibliotech.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record StudentDTO(@NotEmpty @Size(min = 3, max = 10) String rm, @NotEmpty @Size(min = 5, max = 50) String fullName,
                         @NotEmpty @Past LocalDate birthdate, @NotEmpty @Size(min = 11, max = 11) String cellPhone,
                         @NotEmpty AddressDTO addressDTO) {
}
