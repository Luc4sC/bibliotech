package br.com.bibliotech.application.exception;

import br.com.bibliotech.presentation.handler.GeneralException;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class BadRequestException extends GeneralException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST, Instant.now());
    }
}
