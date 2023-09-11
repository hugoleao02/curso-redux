package com.example.myMoneyapp.exception;

public class CustomNotFoundException extends RuntimeException {
    
    public CustomNotFoundException(String message) {
        super(message);
    }
}
