package com.collegeportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequest {
    @Email
    @NotBlank
    private String email;
    public String getEmail() {
        return this.email;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
}

