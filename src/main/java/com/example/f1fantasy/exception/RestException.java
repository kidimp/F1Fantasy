package com.example.f1fantasy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestException extends RuntimeException {

  private final String message;
  private final String[] args;
}
