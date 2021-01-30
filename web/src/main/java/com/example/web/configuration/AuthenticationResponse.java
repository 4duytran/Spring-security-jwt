package com.example.web.configuration;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String accessToken;
    private String userName;

    public AuthenticationResponse(String accessToken, String userName) {
        this.accessToken = accessToken;
        this.userName = userName;
    }
}