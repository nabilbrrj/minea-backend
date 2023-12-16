package com.ecommerce.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends AbstractException {
    public UserAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
