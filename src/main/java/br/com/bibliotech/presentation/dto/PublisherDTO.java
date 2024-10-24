package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PublisherDTO(@NotEmpty(message = "Publisher's name must be specified")
                           @Size(min = 5, max = 50, message = "Publisher's name must have more than 5 letters and " +
                                   "less than 50")
                           String name,
                           @NotEmpty(message = "Publisher's name must be specified")
                           @Size(min = 5, max = 50, message = "Publisher's trade name must have more than 5 letters and " +
                                   "less than 50")
                           String tradeName,
                           @NotNull(message = "Publisher's foundation date must be specified")
                           @PastOrPresent(message = "Publisher's foundation date must be older")
                           LocalDate foundationDate,
                           @NotNull(message = "Publisher's address must be specified")
                           AddressDTO address) {
}
