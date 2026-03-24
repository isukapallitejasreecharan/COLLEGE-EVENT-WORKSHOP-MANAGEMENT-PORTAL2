package com.collegeportal.controllers;

import com.collegeportal.dto.ApiResponse;
import com.collegeportal.dto.RegistrationDto;
import com.collegeportal.entities.RegistrationStatus;
import com.collegeportal.services.RegistrationService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PreAuthorize("hasAnyRole(\'STUDENT\',\'FACULTY\')")
    @PostMapping("/events/{eventId}")
    public ApiResponse<RegistrationDto> register(@PathVariable Long eventId) {
        return ApiResponse.<RegistrationDto>builder().success(true).message("Registration submitted").data(registrationService.register(eventId)).build();
    }

    @PreAuthorize("hasAnyRole(\'STUDENT\',\'FACULTY\')")
    @PatchMapping("/events/{eventId}/cancel")
    public ApiResponse<RegistrationDto> cancel(@PathVariable Long eventId) {
        return ApiResponse.<RegistrationDto>builder().success(true).message("Registration cancelled").data(registrationService.cancel(eventId)).build();
    }

    @PreAuthorize("hasAnyRole(\'STUDENT\',\'FACULTY\')")
    @GetMapping("/my")
    public ApiResponse<List<RegistrationDto>> myRegistrations() {
        return ApiResponse.<List<RegistrationDto>>builder().success(true).message("My registrations fetched").data(registrationService.myRegistrations()).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @GetMapping("/events/{eventId}")
    public ApiResponse<List<RegistrationDto>> participants(@PathVariable Long eventId) {
        return ApiResponse.<List<RegistrationDto>>builder().success(true).message("Participants fetched").data(registrationService.participants(eventId)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PatchMapping("/{registrationId}/status")
    public ApiResponse<RegistrationDto> updateStatus(@PathVariable Long registrationId, @RequestParam RegistrationStatus status, @RequestParam(defaultValue = "") String remarks) {
        return ApiResponse.<RegistrationDto>builder().success(true).message("Registration updated").data(registrationService.updateStatus(registrationId, status, remarks)).build();
    }

    @PreAuthorize("hasAnyRole(\'STUDENT\',\'FACULTY\')")
    @PatchMapping("/events/{eventId}/bookmark")
    public ApiResponse<RegistrationDto> bookmark(@PathVariable Long eventId) {
        return ApiResponse.<RegistrationDto>builder().success(true).message("Bookmark updated").data(registrationService.toggleBookmark(eventId)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @GetMapping("/events/{eventId}/export")
    public ResponseEntity<byte[]> exportParticipants(@PathVariable Long eventId) {
        byte[] csv = registrationService.exportParticipantsCsv(eventId).getBytes(StandardCharsets.UTF_8);
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=participants.csv").body(csv);
    }
    public RegistrationController(final RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
}

