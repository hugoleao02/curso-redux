package com.example.myMoneyapp.utils;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ErrorParserUtil {
    public static List<String> parseErrors(List<FieldError> fieldErrors) {
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getDefaultMessage());
        }
        return errors;
    }
}
