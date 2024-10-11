package br.com.bibliotech.presentation.response;

import java.time.Instant;

public record ErrorResponse(int status, String message, Instant timestamp) {
}
