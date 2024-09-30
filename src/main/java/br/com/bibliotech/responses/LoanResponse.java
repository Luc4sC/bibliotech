package br.com.bibliotech.responses;

import java.time.LocalDate;
import java.util.List;

public record LoanResponse(LocalDate startDate, LocalDate endDate, LocalDate finishedDate, boolean finished, StudentResponse student, List<CopyResponse> copies) {
}
