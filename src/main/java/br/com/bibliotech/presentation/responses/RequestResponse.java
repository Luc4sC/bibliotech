package br.com.bibliotech.presentation.responses;

import java.util.List;

public record RequestResponse(UserResponse userResponse, List<BookResponse> bookResponses, String status) {
}
