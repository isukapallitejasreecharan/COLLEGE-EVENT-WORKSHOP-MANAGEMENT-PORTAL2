package com.collegeportal.controllers;

import com.collegeportal.dto.ApiResponse;
import com.collegeportal.dto.AttendanceDto;
import com.collegeportal.dto.AttendanceRequest;
import com.collegeportal.services.AttendanceService;
import com.collegeportal.services.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final UserService userService;

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\',\'VOLUNTEER\')")
    @PostMapping("/events/{eventId}/check-in")
    public ApiResponse<AttendanceDto> checkIn(@PathVariable Long eventId, @Valid @RequestBody AttendanceRequest request) {
        return ApiResponse.<AttendanceDto>builder().success(true).message("Attendance recorded").data(attendanceService.checkIn(eventId, request)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\',\'VOLUNTEER\')")
    @GetMapping("/events/{eventId}")
    public ApiResponse<List<AttendanceDto>> eventAttendance(@PathVariable Long eventId) {
        return ApiResponse.<List<AttendanceDto>>builder().success(true).message("Attendance fetched").data(attendanceService.eventAttendance(eventId)).build();
    }

    @PreAuthorize("hasAnyRole(\'STUDENT\',\'FACULTY\')")
    @GetMapping("/events/{eventId}/qr")
    public ApiResponse<String> qrPayload(@PathVariable Long eventId, @RequestParam(required = false) Long userId) {
        Long resolvedUserId = userId != null ? userId : userService.getCurrentUserEntity().getId();
        return ApiResponse.<String>builder().success(true).message("QR payload generated").data(attendanceService.generateQrPayload(eventId, resolvedUserId)).build();
    }
    public AttendanceController(final AttendanceService attendanceService, final UserService userService) {
        this.attendanceService = attendanceService;
        this.userService = userService;
    }
}

