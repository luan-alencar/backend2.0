package com.unifacisa.usuarioservice.utils.handler;

import com.unifacisa.usuarioservice.utils.exceptions.CustomServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(CustomServerErrorException.class)
    @ResponseBody
    public ResponseEntity<String> handleCustomServerErrorException(CustomServerErrorException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no servidor externo: " + ex.getMessage());
    }

}
