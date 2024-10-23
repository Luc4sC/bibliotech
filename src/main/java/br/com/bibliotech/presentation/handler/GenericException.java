package br.com.bibliotech.presentation.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
@Getter
public abstract class GenericException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final Instant timestamp;

    public GenericException(String message, HttpStatus httpStatus, Instant timestamp) {
        super(message);
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

}
