package org.top.countrydirectoryapp.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.top.countrydirectoryapp.api.message.CommonApiMessages;

@RestController
public class StatusController {

    @GetMapping("/api")
    public CommonApiMessages.StatusResponse getStatus() {
        return new CommonApiMessages.StatusResponse("server is running", "localhost:8080", "http");
    }

    @GetMapping("/api/ping")
    public CommonApiMessages.PingResponse ping() {
        return new CommonApiMessages.PingResponse("pong");
    }
}