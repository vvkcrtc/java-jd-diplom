package ru.netology.jddiplom.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
    @JsonProperty("auth-token")
    String authToken;

    public AuthResponse(String authToken) {
        this.authToken = authToken;
    }
}
