package com.example.f1fantasy.exception;

public class DriverAlreadyExistsException extends RestException {


    public DriverAlreadyExistsException(String message) {
        super(message, null);
    }
}
