package com.collegeportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private boolean rememberMe;
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public boolean isRememberMe() {
        return this.rememberMe;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    public void setPassword(final String password) {
        this.password = password;
    }
    public void setRememberMe(final boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}

