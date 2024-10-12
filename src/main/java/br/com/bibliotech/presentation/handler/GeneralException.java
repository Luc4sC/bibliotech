package br.com.bibliotech.presentation.handler;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public abstract class GeneralException extends RuntimeException {

    public final HttpStatus HTTP_STATUS;
    public final Instant TIMESTAMP;

    public GeneralException(String message, HttpStatus httpStatus, Instant timestamp){
        super(message);
        this.HTTP_STATUS = httpStatus;
        this.TIMESTAMP = timestamp;
    }
}
