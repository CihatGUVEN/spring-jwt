package com.cihatguven.rest.auth.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends RuntimeException{

    @Getter
    private final HttpStatus httpStatus;

    public UnAuthorizedException(String message) {
        super(message);
        this.httpStatus = HttpStatus.UNAUTHORIZED;
    }
}
