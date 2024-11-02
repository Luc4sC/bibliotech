package br.com.bibliotech.presentation.handler;

import br.com.bibliotech.infrastructure.exception.NotFoundException;
import br.com.bibliotech.presentation.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorResponse> handle(NotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value(),
                Instant.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
