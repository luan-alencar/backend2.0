package com.unifacisa.shoppingcartservice.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClientErrorException extends ResponseStatusException {



    public ClientErrorException(HttpStatus status) {
        super(status);
    }

    public ClientErrorException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public ClientErrorException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public ClientErrorException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }
}

