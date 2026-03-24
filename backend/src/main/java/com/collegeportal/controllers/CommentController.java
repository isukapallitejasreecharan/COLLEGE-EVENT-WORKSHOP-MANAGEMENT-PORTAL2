package com.collegeportal.controllers;

import com.collegeportal.dto.ApiResponse;
import com.collegeportal.dto.CommentDto;
import com.collegeportal.dto.CommentRequest;
import com.collegeportal.services.CommentService;
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
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/events/{eventId}")
    public ApiResponse<List<CommentDto>> eventComments(@PathVariable Long eventId) {
        return ApiResponse.<List<CommentDto>>builder().success(true).message("Comments fetched").data(commentService.eventComments(eventId)).build();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/events/{eventId}")
    public ApiResponse<CommentDto> add(@PathVariable Long eventId, @Valid @RequestBody CommentRequest request) {
        return ApiResponse.<CommentDto>builder().success(true).message("Comment added").data(commentService.add(eventId, request)).build();
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{commentId}/upvote")
    public ApiResponse<CommentDto> upvote(@PathVariable Long commentId) {
        return ApiResponse.<CommentDto>builder().success(true).message("Comment upvoted").data(commentService.upvote(commentId)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PatchMapping("/{commentId}/moderate")
    public ApiResponse<CommentDto> moderate(@PathVariable Long commentId, @RequestParam boolean approved) {
        return ApiResponse.<CommentDto>builder().success(true).message("Comment moderated").data(commentService.moderate(commentId, approved)).build();
    }
    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }
}

