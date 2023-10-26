package com.unifacisa.usuarioservice.utils.exceptions;

public class CustomServerErrorException extends RuntimeException {

    public CustomServerErrorException() {
        super();
    }

    public CustomServerErrorException(String message) {
        super(message);
    }
}
