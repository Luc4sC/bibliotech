package br.com.bibliotech.presentation.handler;

import br.com.bibliotech.presentation.responses.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GenericException.class)
    private ResponseEntity<ErrorResponse> handleException(GenericException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getHttpStatus().value(), e.getTimestamp());
        return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
    }

}
