package com.collegeportal.controllers;

import com.collegeportal.dto.ApiResponse;
import com.collegeportal.dto.FeedbackDto;
import com.collegeportal.dto.FeedbackRequest;
import com.collegeportal.services.FeedbackService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PreAuthorize("hasAnyRole(\'STUDENT\',\'FACULTY\')")
    @PostMapping("/events/{eventId}")
    public ApiResponse<FeedbackDto> submit(@PathVariable Long eventId, @Valid @RequestBody FeedbackRequest request) {
        return ApiResponse.<FeedbackDto>builder().success(true).message("Feedback submitted").data(feedbackService.submit(eventId, request)).build();
    }

    @GetMapping("/events/{eventId}")
    public ApiResponse<List<FeedbackDto>> eventFeedback(@PathVariable Long eventId) {
        return ApiResponse.<List<FeedbackDto>>builder().success(true).message("Feedback fetched").data(feedbackService.eventFeedback(eventId)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PatchMapping("/{feedbackId}/moderate")
    public ApiResponse<FeedbackDto> moderate(@PathVariable Long feedbackId, @RequestParam boolean approved) {
        return ApiResponse.<FeedbackDto>builder().success(true).message("Feedback moderated").data(feedbackService.moderate(feedbackId, approved)).build();
    }
    public FeedbackController(final FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
}

