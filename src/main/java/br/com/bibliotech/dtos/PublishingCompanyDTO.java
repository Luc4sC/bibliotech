package br.com.bibliotech.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PublishingCompanyDTO(@NotEmpty @Size(min = 5, max = 50) String tradeName,
                                   @NotEmpty @Size(min = 5, max = 50) String name,
                                   @NotNull @PastOrPresent LocalDate foundationDate,
                                   @NotNull AddressDTO addressDTO) {
}
