package com.example.api;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class AppError {

    private int statusCode;
    private String message;

    public AppError(int statusCode, String message) {
        this.statusCode= statusCode;
        this.message = message;
    }

    public AppError() {

    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setStatusCode(int code) {
        statusCode = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
