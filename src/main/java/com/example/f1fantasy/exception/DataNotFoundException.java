package com.example.f1fantasy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RestException {

    public DataNotFoundException(String message) {
        super(message, null);
    }
}