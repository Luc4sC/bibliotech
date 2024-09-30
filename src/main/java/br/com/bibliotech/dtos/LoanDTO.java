package br.com.bibliotech.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record LoanDTO(@NotEmpty String rm, @NotEmpty List<Long> copiesIds) {
}
