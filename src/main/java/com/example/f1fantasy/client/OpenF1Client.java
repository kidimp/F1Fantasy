package com.example.f1fantasy.client;

import com.example.f1fantasy.model.dto.external.ExternalDriverDataDTO;
import com.example.f1fantasy.model.dto.external.ExternalMeetingDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "open-f1", url = "${feign.client.config.open-f1.url}")
public interface OpenF1Client {

    @GetMapping("/drivers")
    List<ExternalDriverDataDTO> getDriversByMeetingKey(@RequestParam("meeting_key") int meetingKey);

    @GetMapping("/meetings")
    List<ExternalMeetingDataDTO> getMeetings(@RequestParam("year") int year);
}
