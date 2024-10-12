package br.com.bibliotech.domain.exception;

import br.com.bibliotech.presentation.handler.GeneralException;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class ConflictException extends GeneralException {
    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT, Instant.now());
    }
}
