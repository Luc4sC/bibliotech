package br.com.bibliotech.presentation.response;

import java.time.LocalDate;

public record AuthorResponse(String fullName, String stageName, LocalDate birthdate) {
}
