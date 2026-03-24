package com.collegeportal.controllers;

import com.collegeportal.dto.ApiResponse;
import com.collegeportal.dto.EventDto;
import com.collegeportal.dto.EventRequest;
import com.collegeportal.dto.FileUploadResponse;
import com.collegeportal.dto.PageResponse;
import com.collegeportal.dto.VolunteerAssignmentRequest;
import com.collegeportal.entities.EventStatus;
import com.collegeportal.services.EventService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ApiResponse<PageResponse<EventDto>> listEvents(@RequestParam(required = false) String search, @RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long departmentId, @RequestParam(required = false) EventStatus status, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "startDate") String sortBy, @RequestParam(defaultValue = "ASC") String direction) {
        return ApiResponse.<PageResponse<EventDto>>builder().success(true).message("Events fetched").data(eventService.listEvents(search, categoryId, departmentId, status, page, size, sortBy, direction)).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<EventDto> getEvent(@PathVariable Long id) {
        return ApiResponse.<EventDto>builder().success(true).message("Event fetched").data(eventService.getEvent(id)).build();
    }

    @GetMapping("/calendar")
    public ApiResponse<List<EventDto>> calendar(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return ApiResponse.<List<EventDto>>builder().success(true).message("Calendar events fetched").data(eventService.getCalendar(from, to)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @GetMapping("/mine")
    public ApiResponse<List<EventDto>> mine() {
        return ApiResponse.<List<EventDto>>builder().success(true).message("Managed events fetched").data(eventService.getMyManagedEvents()).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PostMapping
    public ApiResponse<EventDto> createEvent(@Valid @RequestBody EventRequest request) {
        return ApiResponse.<EventDto>builder().success(true).message("Event created").data(eventService.createEvent(request)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PutMapping("/{id}")
    public ApiResponse<EventDto> updateEvent(@PathVariable Long id, @Valid @RequestBody EventRequest request) {
        return ApiResponse.<EventDto>builder().success(true).message("Event updated").data(eventService.updateEvent(id, request)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PostMapping("/{id}/publish")
    public ApiResponse<EventDto> publish(@PathVariable Long id) {
        return ApiResponse.<EventDto>builder().success(true).message("Event published").data(eventService.publishEvent(id)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ApiResponse.<Void>builder().success(true).message("Event deleted").build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PostMapping("/{id}/poster")
    public ApiResponse<FileUploadResponse> uploadPoster(@PathVariable Long id, @RequestPart("file") MultipartFile file) {
        return ApiResponse.<FileUploadResponse>builder().success(true).message("Poster uploaded").data(eventService.uploadPoster(id, file)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PostMapping("/{id}/materials")
    public ApiResponse<FileUploadResponse> uploadMaterials(@PathVariable Long id, @RequestPart("file") MultipartFile file) {
        return ApiResponse.<FileUploadResponse>builder().success(true).message("Materials uploaded").data(eventService.uploadMaterials(id, file)).build();
    }

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PostMapping("/{id}/volunteers")
    public ApiResponse<EventDto> assignVolunteer(@PathVariable Long id, @Valid @RequestBody VolunteerAssignmentRequest request) {
        return ApiResponse.<EventDto>builder().success(true).message("Volunteer assigned").data(eventService.assignVolunteer(id, request)).build();
    }

    @PreAuthorize("hasRole(\'VOLUNTEER\')")
    @GetMapping("/assigned/volunteer")
    public ApiResponse<List<EventDto>> assignedVolunteerEvents() {
        return ApiResponse.<List<EventDto>>builder().success(true).message("Assigned volunteer events fetched").data(eventService.getAssignedVolunteerEvents()).build();
    }
    public EventController(final EventService eventService) {
        this.eventService = eventService;
    }
}

