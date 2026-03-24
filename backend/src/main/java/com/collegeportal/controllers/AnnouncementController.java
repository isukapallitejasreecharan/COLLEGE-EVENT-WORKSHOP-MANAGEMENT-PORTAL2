package com.collegeportal.controllers;

import com.collegeportal.dto.AnnouncementDto;
import com.collegeportal.dto.AnnouncementRequest;
import com.collegeportal.dto.ApiResponse;
import com.collegeportal.services.AnnouncementService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @GetMapping
    public ApiResponse<List<AnnouncementDto>> list() {
        return ApiResponse.<List<AnnouncementDto>>builder().success(true).message("Announcements fetched").data(announcementService.listActive()).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PostMapping
    public ApiResponse<AnnouncementDto> create(@Valid @RequestBody AnnouncementRequest request) {
        return ApiResponse.<AnnouncementDto>builder().success(true).message("Announcement created").data(announcementService.create(request)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PutMapping("/{id}")
    public ApiResponse<AnnouncementDto> update(@PathVariable Long id, @Valid @RequestBody AnnouncementRequest request) {
        return ApiResponse.<AnnouncementDto>builder().success(true).message("Announcement updated").data(announcementService.update(id, request)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        announcementService.delete(id);
        return ApiResponse.<Void>builder().success(true).message("Announcement deleted").build();
    }
    public AnnouncementController(final AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }
}

