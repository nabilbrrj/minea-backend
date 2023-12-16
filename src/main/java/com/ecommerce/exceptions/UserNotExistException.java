package com.ecommerce.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotExistException extends AbstractException{
    public UserNotExistException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpCode() {
        return null;
    }
}
