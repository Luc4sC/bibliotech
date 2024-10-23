package br.com.bibliotech.application.exception;

import br.com.bibliotech.presentation.handler.GenericException;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class BadRequestException extends GenericException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST, Instant.now());
    }
}
