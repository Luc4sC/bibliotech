package br.com.bibliotech.responses;

import java.time.LocalDate;

public record AuthorResponse(String fullName, String stageName, LocalDate birthdate) {
}
