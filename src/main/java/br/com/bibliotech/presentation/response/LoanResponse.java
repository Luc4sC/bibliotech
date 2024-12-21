package br.com.bibliotech.presentation.response;

import java.time.LocalDate;

public record LoanResponse(LocalDate startDate, LocalDate endDate, LocalDate finishDate, boolean isFinished, String loanRequestUrl) {
}
