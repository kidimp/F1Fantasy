package com.example.f1fantasy.exception;

public class ExternalApiException extends RestException {

    public ExternalApiException(String message) {
        super(message, null);
    }
}