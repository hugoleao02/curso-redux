package com.myMoneyapp.exception;

public class CustomBadRequestException extends RuntimeException {
    
    public CustomBadRequestException(String message) {
        super(message);
    }
}
