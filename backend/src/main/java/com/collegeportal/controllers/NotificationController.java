package com.collegeportal.controllers;

import com.collegeportal.dto.ApiResponse;
import com.collegeportal.dto.NotificationDto;
import com.collegeportal.entities.Notification;
import com.collegeportal.services.NotificationService;
import com.collegeportal.services.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    private final UserService userService;

    @GetMapping("/my")
    public ApiResponse<List<NotificationDto>> myNotifications() {
        Long userId = userService.getCurrentUserEntity().getId();
        return ApiResponse.<List<NotificationDto>>builder().success(true).message("Notifications fetched").data(notificationService.getMyNotifications(userId)).build();
    }

    @GetMapping("/unread-count")
    public ApiResponse<Long> unreadCount() {
        Long userId = userService.getCurrentUserEntity().getId();
        return ApiResponse.<Long>builder().success(true).message("Unread count fetched").data(notificationService.unreadCount(userId)).build();
    }

    @PatchMapping("/{id}/read")
    public ApiResponse<Void> markRead(@PathVariable Long id) {
        Notification notification = notificationService.findById(id);
        notificationService.markRead(notification);
        return ApiResponse.<Void>builder().success(true).message("Notification marked as read").build();
    }
    public NotificationController(final NotificationService notificationService, final UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }
}

