package br.com.bibliotech.presentation.response;

import java.time.LocalDate;

public record LoanRequestResponse(String userUrl, String booksUrl, LocalDate requestDate,
                                  String status) {
}
