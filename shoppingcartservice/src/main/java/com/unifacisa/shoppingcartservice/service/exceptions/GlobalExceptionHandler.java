package com.unifacisa.shoppingcartservice.service.exceptions;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@ControllerAdvice
@Order
@Component
public class GlobalExceptionHandler extends ResponseStatusException {


    public GlobalExceptionHandler() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public GlobalExceptionHandler(HttpStatus status) {
        super(status);
    }

    public GlobalExceptionHandler(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public GlobalExceptionHandler(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public GlobalExceptionHandler(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String reason = "Internal Server Error";
        String message = ex.getMessage();

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(), status.value(), reason, message);

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(GlobalExceptionHandler.class)
    public ResponseEntity<ErrorResponse> handleGlobalExceptionHandler(GlobalExceptionHandler ex, WebRequest request) {
        HttpStatusCode status = ex.getStatusCode();
        String reason = ex.getReason();
        String message = ex.getMessage();

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(), status.value(), reason, message);

        return new ResponseEntity<>(errorResponse, status);
    }
}
