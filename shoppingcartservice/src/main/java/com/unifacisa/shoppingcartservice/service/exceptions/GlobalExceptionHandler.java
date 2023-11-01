package com.unifacisa.shoppingcartservice.service.exceptions;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@ControllerAdvice
@Order
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        HttpStatus status = ex.getStatus();
        String reason = ex.getReason();
        String message = ex.getMessage();

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(), status.value(), reason, message);

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String reason = "Internal Server Error";
        String message = ex.getMessage();

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(), status.value(), reason, message);

        return new ResponseEntity<>(errorResponse, status);
    }
}
