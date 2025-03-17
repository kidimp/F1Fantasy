package com.example.f1fantasy.client;

import com.example.f1fantasy.model.dto.external.ExternalDriverDataDTO;
import com.example.f1fantasy.model.dto.external.ExternalMeetingDataDTO;
import com.example.f1fantasy.model.dto.external.ExternalSessionDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "open-f1", url = "${feign.client.config.open-f1.url}")
public interface OpenF1Client {

    @GetMapping("/drivers")
    List<ExternalDriverDataDTO> getDriversByMeetingKey(@RequestParam("meeting_key") int meetingKey);

    @GetMapping("/drivers")
    List<ExternalDriverDataDTO> getDriversBySessionKey(@RequestParam("session_key") int sessionKey);

    @GetMapping("/meetings")
    List<ExternalMeetingDataDTO> getMeetingsByYear(@RequestParam("year") int year);

    @GetMapping("/sessions")
    List<ExternalSessionDataDTO> getSessionsByMeetingKey(@RequestParam("meeting_key") int meetingKey);
}
