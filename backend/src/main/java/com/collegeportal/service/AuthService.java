package com.collegeportal.service;

import com.collegeportal.dto.AuthDtos;

public interface AuthService {
    AuthDtos.TokenResponse register(AuthDtos.RegisterRequest request);
    AuthDtos.TokenResponse login(AuthDtos.LoginRequest request);
    AuthDtos.TokenResponse refresh(String refreshToken);
}
