package com.ecommerce.exceptions;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistException extends AbstractException{
    public ProductAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpCode() {
        return HttpStatus.CONFLICT;
    }
}
