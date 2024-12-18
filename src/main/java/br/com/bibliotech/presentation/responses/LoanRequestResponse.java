package br.com.bibliotech.presentation.responses;

import java.time.LocalDate;
import java.util.List;

public record LoanRequestResponse(String userUrl, String booksUrl, LocalDate requestDate,
                                  String status) {
}
