package br.com.bibliotech.presentation.responses;

import java.time.Instant;

public record ErrorResponse(String message, int statusCode, Instant timestamp) {
}
