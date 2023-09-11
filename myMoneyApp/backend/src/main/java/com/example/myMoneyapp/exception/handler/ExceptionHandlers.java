package com.example.myMoneyapp.exception.handler;

import com.example.myMoneyapp.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlers {
    
    private final ParseErrorService parseErrorService;
    
    @Autowired
    public ExceptionHandlers(ParseErrorService parseErrorService) {
        this.parseErrorService = parseErrorService;
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ParseError> handleGenericException(Exception ex, WebRequest request) {
        ParseError parseError = parseErrorService.createParseError(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(parseError);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ParseError>> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ParseError> parseErrors = fieldErrors.stream()
                .map(error -> parseErrorService.createParseError(
                        ex, HttpStatus.BAD_REQUEST, error.getDefaultMessage(), request))
                .collect(Collectors.toList());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(parseErrors);
    }
    
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ParseError> handleCustomNotFoundException(CustomNotFoundException ex, WebRequest request) {
        ParseError parseError = parseErrorService.createParseError(ex, HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(parseError);
    }
    
    @ExceptionHandler(CustomConflictException.class)
    public ResponseEntity<ParseError> handleCustomConflictException(CustomConflictException ex, WebRequest request) {
        ParseError parseError = parseErrorService.createParseError(ex, HttpStatus.CONFLICT, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(parseError);
    }
    
    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ParseError> handleCustomBadRequestException(CustomBadRequestException ex, WebRequest request) {
        ParseError parseError = parseErrorService.createParseError(ex, HttpStatus.BAD_REQUEST, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(parseError);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ParseError> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        int rawStatusCode = ex.getRawStatusCode();
        HttpStatus status = HttpStatus.valueOf(rawStatusCode);
        ParseError parseError = parseErrorService.createParseError(ex, status, ex.getMessage(), request);
        return ResponseEntity.status(status).body(parseError);
    }
    
}
