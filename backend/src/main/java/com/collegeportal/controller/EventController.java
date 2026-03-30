package com.collegeportal.controller;

import com.collegeportal.dto.EventDtos;
import com.collegeportal.entity.EventCategory;
import com.collegeportal.service.EventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public Page<EventDtos.EventResponse> list(@RequestParam(required = false) EventCategory category, Pageable pageable) {
        return eventService.listPublished(category, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN', 'SUPER_ADMIN')")
    public EventDtos.EventResponse create(@Valid @RequestBody EventDtos.EventCreateRequest request, Authentication authentication) {
        return eventService.create(request, authentication.getName());
    }

    @PostMapping("/{id}/register")
    @PreAuthorize("hasRole('STUDENT')")
    public void register(@PathVariable Long id, Authentication authentication) {
        eventService.register(id, authentication.getName());
    }
}
