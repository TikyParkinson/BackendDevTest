package com.aruimari.springboot.di.app.exceptions;

import com.aruimari.springboot.di.app.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<Error> handleRuntimeException(RuntimeException ex) {
        return getErrorResponseEntity(ex.getMessage(),ex.getClass().getCanonicalName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Error> handleProductNotFound(ProductNotFoundException ex) {
        return getErrorResponseEntity(ex.getMessage(), ex.getClass().getCanonicalName(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> handleNoHandlerFound(NoHandlerFoundException ex) {
        return getErrorResponseEntity(ex.getMessage(), ex.getClass().getCanonicalName(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Error> getErrorResponseEntity(String message, String errorType,
                                                         HttpStatus status)  {
        Error errorMessage = new Error();
        errorMessage.setDate(new Date());
        errorMessage.setHttpStatus(status.value());
        errorMessage.setErrorType(errorType);
        errorMessage.setMessage(message);

        return ResponseEntity.status(status).body(errorMessage);
    }

}
