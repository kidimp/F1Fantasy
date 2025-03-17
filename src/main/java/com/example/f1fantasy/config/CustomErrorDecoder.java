package com.example.f1fantasy.config;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new RuntimeException("Resource not found: " + methodKey);
        }
        // Можно добавить обработку других ошибок
        return defaultDecoder.decode(methodKey, response);
    }
}
