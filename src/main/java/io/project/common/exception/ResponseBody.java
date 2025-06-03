package io.project.common.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseBody{

    private final String message;
    private final String timestamp;

    public ResponseBody(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
