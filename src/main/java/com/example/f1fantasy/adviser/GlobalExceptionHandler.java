package com.example.f1fantasy.adviser;

import com.example.f1fantasy.exception.DataNotFoundException;
import com.example.f1fantasy.exception.DriverAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String STATUS = "status";
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";

    @ExceptionHandler(DriverAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleDriverAlreadyExists(DriverAlreadyExistsException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(STATUS, HttpStatus.CONFLICT.value()); // 409
        errorResponse.put(ERROR, HttpStatus.CONFLICT.getReasonPhrase()); // "Conflict"
        errorResponse.put(MESSAGE, ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(STATUS, HttpStatus.BAD_REQUEST.value()); // 400
        errorResponse.put(ERROR, HttpStatus.BAD_REQUEST.getReasonPhrase()); // "Bad Request"
        errorResponse.put(MESSAGE, ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleDataNotExistsException(DataNotFoundException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(STATUS, HttpStatus.NOT_FOUND.value()); // 404
        errorResponse.put(ERROR, HttpStatus.NOT_FOUND.getReasonPhrase()); // "Not Found"
        errorResponse.put(MESSAGE, e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
