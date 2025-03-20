package com.example.f1fantasy.config;

import com.example.f1fantasy.exception.ExternalApiException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new RuntimeException("Bad request while calling: " + methodKey + ". Please check your input.");
            case 404 -> new RuntimeException("Resource not found: " + methodKey);
            case 500 -> new ExternalApiException("The external API is currently unavailable.");
            case 503 -> new ExternalApiException("The external service is temporarily unavailable.");
            default ->
                // Для всех других ошибок используем стандартный обработчик
                    defaultDecoder.decode(methodKey, response);
        };
    }
}