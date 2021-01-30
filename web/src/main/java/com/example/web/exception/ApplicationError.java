package com.example.web.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApplicationError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now();
    private HttpStatus status;
    private String message;
    private String path;

    public ApplicationError(HttpStatus status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }
}

