package br.com.bibliotech.presentation.response;

import java.time.Instant;

public record ErrorResponse(String message, int statusCode, Instant timestamp) {
}
