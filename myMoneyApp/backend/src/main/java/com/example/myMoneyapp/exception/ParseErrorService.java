package com.example.myMoneyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Service
public class ParseErrorService {
    
    public ParseError createParseError(Exception ex, HttpStatus status, String message, WebRequest request) {
        String timestamp = LocalDateTime.now().toString();
        String error = status.getReasonPhrase();
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        
        return new ParseError(timestamp, status.value(), error, message, path);
    }
}
