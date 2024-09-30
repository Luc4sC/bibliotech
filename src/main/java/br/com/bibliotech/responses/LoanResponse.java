package br.com.bibliotech.responses;

import java.time.LocalDate;
import java.util.List;

public record LoanResponse(LocalDate startDate, LocalDate endDate, LocalDate finishedDate, StudentResponse student, List<CopyResponse> copies) {
}
