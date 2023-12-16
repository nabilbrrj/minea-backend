package com.ecommerce.exceptions;

import org.springframework.http.HttpStatus;

public abstract class AbstractException extends Exception{
    private String msg ;

    public AbstractException(String message) {
        super(message);
        this.msg = message;
    }
    public abstract HttpStatus getHttpCode();

}
