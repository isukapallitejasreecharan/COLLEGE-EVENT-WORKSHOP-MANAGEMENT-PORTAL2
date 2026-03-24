package com.collegeportal.controllers;

import com.collegeportal.dto.ApiResponse;
import com.collegeportal.dto.FileUploadResponse;
import com.collegeportal.dto.PageResponse;
import com.collegeportal.dto.RoleAssignmentRequest;
import com.collegeportal.dto.UserDto;
import com.collegeportal.dto.UserUpdateRequest;
import com.collegeportal.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ApiResponse<UserDto> me() {
        return ApiResponse.<UserDto>builder().success(true).message("Profile fetched").data(userService.getCurrentUser()).build();
    }

    @PatchMapping("/me")
    public ApiResponse<UserDto> updateProfile(@Valid @org.springframework.web.bind.annotation.RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserDto>builder().success(true).message("Profile updated").data(userService.updateCurrentUser(request)).build();
    }

    @PostMapping("/me/avatar")
    public ApiResponse<FileUploadResponse> uploadAvatar(@RequestPart("file") MultipartFile file) {
        return ApiResponse.<FileUploadResponse>builder().success(true).message("Avatar uploaded").data(userService.uploadAvatar(file)).build();
    }

    @PreAuthorize("hasRole(\'ADMIN\')")
    @GetMapping
    public ApiResponse<PageResponse<UserDto>> listUsers(@RequestParam(defaultValue = "") String query, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "createdAt") String sortBy, @RequestParam(defaultValue = "DESC") String direction) {
        return ApiResponse.<PageResponse<UserDto>>builder().success(true).message("Users fetched").data(userService.listUsers(query, page, size, sortBy, direction)).build();
    }

    @PreAuthorize("hasRole(\'ADMIN\')")
    @PatchMapping("/{userId}/roles")
    public ApiResponse<UserDto> assignRoles(@PathVariable Long userId, @Valid @org.springframework.web.bind.annotation.RequestBody RoleAssignmentRequest request) {
        return ApiResponse.<UserDto>builder().success(true).message("Roles updated").data(userService.assignRoles(userId, request)).build();
    }
    public UserController(final UserService userService) {
        this.userService = userService;
    }
}

