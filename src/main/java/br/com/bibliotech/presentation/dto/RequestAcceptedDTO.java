package br.com.bibliotech.presentation.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RequestAcceptedDTO(@NotNull @Future LocalDate endDate) {
}
