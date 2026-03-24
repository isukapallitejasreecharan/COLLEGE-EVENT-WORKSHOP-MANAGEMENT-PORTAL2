package com.collegeportal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ResetPasswordRequest {
    @NotBlank
    private String token;
    @NotBlank
    @Size(min = 8)
    private String password;
    public String getToken() {
        return this.token;
    }
    public String getPassword() {
        return this.password;
    }
    public void setToken(final String token) {
        this.token = token;
    }
    public void setPassword(final String password) {
        this.password = password;
    }
}

