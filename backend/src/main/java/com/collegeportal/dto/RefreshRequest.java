package com.collegeportal.dto;

import jakarta.validation.constraints.NotBlank;

public class RefreshRequest {
    @NotBlank
    private String refreshToken;
    public String getRefreshToken() {
        return this.refreshToken;
    }
    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

