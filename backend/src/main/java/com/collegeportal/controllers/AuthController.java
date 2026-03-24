package com.collegeportal.controllers;

import com.collegeportal.dto.ApiResponse;
import com.collegeportal.dto.AuthResponse;
import com.collegeportal.dto.ForgotPasswordRequest;
import com.collegeportal.dto.LoginRequest;
import com.collegeportal.dto.RefreshRequest;
import com.collegeportal.dto.RegisterRequest;
import com.collegeportal.dto.ResetPasswordRequest;
import com.collegeportal.services.AuthService;
import com.collegeportal.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ApiResponse.<AuthResponse>builder().success(true).message("Registration successful").data(authService.register(request)).build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.<AuthResponse>builder().success(true).message("Login successful").data(authService.login(request)).build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(@Valid @RequestBody RefreshRequest request) {
        return ApiResponse.<AuthResponse>builder().success(true).message("Token refreshed").data(authService.refresh(request)).build();
    }

    @PostMapping("/forgot-password")
    public ApiResponse<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request);
        return ApiResponse.<Void>builder().success(true).message("Password reset email sent").build();
    }

    @PostMapping("/reset-password")
    public ApiResponse<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request);
        return ApiResponse.<Void>builder().success(true).message("Password reset successful").build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestParam String refreshToken) {
        authService.logout(refreshToken);
        return ApiResponse.<Void>builder().success(true).message("Logout successful").build();
    }

    @GetMapping("/verify-email")
    public ApiResponse<Void> verifyEmail(@RequestParam String token) {
        authService.verifyEmail(token);
        return ApiResponse.<Void>builder().success(true).message("Email verified successfully").build();
    }

    @GetMapping("/me")
    public ApiResponse<Object> me() {
        return ApiResponse.builder().success(true).message("Current user fetched").data(userService.getCurrentUser()).build();
    }
    public AuthController(final AuthService authService, final UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }
}

