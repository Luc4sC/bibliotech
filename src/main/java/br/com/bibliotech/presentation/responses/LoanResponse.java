package br.com.bibliotech.presentation.responses;

import java.time.LocalDate;

public record LoanResponse(LocalDate startDate, LocalDate endDate, LocalDate finishDate, String loanRequestUrl) {
}
