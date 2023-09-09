package com.myMoneyapp.exception;

public class CustomConflictException extends RuntimeException {
    
    public CustomConflictException(String message) {
        super(message);
    }
    
    public CustomConflictException(String message, Exception e) {
    
    }
}
