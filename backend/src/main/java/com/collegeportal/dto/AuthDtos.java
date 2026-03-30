package com.collegeportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthDtos {
    public record RegisterRequest(
            @NotBlank String fullName,
            @Email String email,
            @Size(min = 8) String password
    ) {}

    public record LoginRequest(@Email String email, @NotBlank String password) {}

    public record TokenResponse(String accessToken, String refreshToken, String tokenType) {}
}
