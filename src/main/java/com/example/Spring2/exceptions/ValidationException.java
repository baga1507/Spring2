package com.example.Spring2.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {

    private List<String> errors;

    public ValidationException(List<String> errors) {
        super(errors.stream().collect(Collectors.joining(", ")));
        this.errors = errors;
    }
}
