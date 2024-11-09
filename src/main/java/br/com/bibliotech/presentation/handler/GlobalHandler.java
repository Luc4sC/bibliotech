package br.com.bibliotech.presentation.handler;

import br.com.bibliotech.infrastructure.exception.ConflictException;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import br.com.bibliotech.presentation.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponse handle(NotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    private ErrorResponse handle(ConflictException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.CONFLICT.value(), Instant.now());
    }

}
