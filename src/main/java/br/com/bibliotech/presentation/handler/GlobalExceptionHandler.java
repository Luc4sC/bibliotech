package br.com.bibliotech.presentation.handler;

import br.com.bibliotech.presentation.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponse> notFoundHandler(GeneralException e){
        ErrorResponse errorResponse = new ErrorResponse(e.HTTP_STATUS.value(), e.getMessage(), e.TIMESTAMP);
        log.info(e.toString());
        return ResponseEntity.status(e.HTTP_STATUS).body(errorResponse);
    }

}
