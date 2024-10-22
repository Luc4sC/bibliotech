package br.com.bibliotech.infrastructure.exception;

import br.com.bibliotech.presentation.handler.GenericException;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class NotFoundException extends GenericException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, Instant.now());
    }

}
