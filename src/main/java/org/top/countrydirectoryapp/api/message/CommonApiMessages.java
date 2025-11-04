package org.top.countrydirectoryapp.api.message;

public class CommonApiMessages {

    public record StatusResponse(String status, String host, String protocol) {}

    public record PingResponse(String status) {}

    public record ErrorMessage(String code, String details) {}
}