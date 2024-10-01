package br.com.bibliotech.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LoanDTO(@NotEmpty String rm, @NotNull List<Long> copiesIds) {
}
