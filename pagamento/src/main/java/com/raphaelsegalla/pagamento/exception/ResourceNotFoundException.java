package com.raphaelsegalla.pagamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3056913595301424377L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
