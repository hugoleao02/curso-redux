package com.myMoneyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CustomNotFoundException extends RuntimeException {
    
    public CustomNotFoundException(String message) {
        super(message);
    }
}
