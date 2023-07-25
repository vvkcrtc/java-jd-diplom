package ru.netology.jddiplom.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
    @JsonProperty("auth-token")
    AuthToken authToken;

    public AuthResponse(AuthToken authToken) {
        this.authToken = authToken;
    }
}
