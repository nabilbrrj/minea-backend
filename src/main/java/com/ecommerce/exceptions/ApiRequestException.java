package com.ecommerce.exceptions;

    import lombok.Getter;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    @RequiredArgsConstructor
    @Getter
    public class ApiRequestException extends RuntimeException{
       private final  HttpStatus httpStatus;

        public ApiRequestException(String message, HttpStatus httpStatus) {
            super(message);
            this.httpStatus = httpStatus;
        }
    }
