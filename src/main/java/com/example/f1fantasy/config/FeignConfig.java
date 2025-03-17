package com.example.f1fantasy.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableFeignClients(basePackages = {"com.example.f1fantasy.client"})
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // Логирование запросов и ответов
    }

    @Bean
    public Request.Options requestOptions() {
        // Таймауты: 5 секунд на подключение, 10 секунд на чтение и перенаправления включены
        return new Request.Options(
                5000L, TimeUnit.MILLISECONDS, // Таймаут на подключение в миллисекундах
                10000L, TimeUnit.MILLISECONDS, // Таймаут на чтение в миллисекундах
                true // Разрешаем следовать редиректам
        );
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder(); // Кастомный обработчик ошибок
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 5000, 3); // Настройка повторных попыток
    }
}
