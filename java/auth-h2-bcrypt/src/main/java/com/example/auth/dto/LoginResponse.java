package com.example.auth.dto;

public record LoginResponse(
        boolean authenticated,
        String message
) {
}
