package br.com.bibliotech.responses;

import java.time.LocalDate;
import java.util.List;

public record AuthorResponse(String fullName, String stageName, LocalDate birthdate, List<BookResponse> books) {
}
