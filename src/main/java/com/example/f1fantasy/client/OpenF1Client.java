package com.example.f1fantasy.client;

import com.example.f1fantasy.model.dto.DriverDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "open-f1", url = "${feign.client.config.open-f1.url}")
public interface OpenF1Client {
    @GetMapping("/drivers")
    List<DriverDTO> getDrivers();
}
