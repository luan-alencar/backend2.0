package com.unifacisa.shoppingcartservice.service.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ClientErrorException.class)
    public ResponseEntity<ErrorResponse> handlerCustomErrorException(ClientErrorException clientErrorException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(Instant.now().toString());
        errorResponse.setError("Bad Request");
        errorResponse.setMessage(clientErrorException.getMessage());
        return ResponseEntity.status(clientErrorException.getStatus()).body(errorResponse);
    }
    
}
