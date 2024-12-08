package br.com.bibliotech.presentation.responses;

import java.time.LocalDate;
import java.util.List;

public record RequestResponse(UserResponse userResponse, List<BookResponse> bookResponses, LocalDate requestDate,
                              String status) {
}
