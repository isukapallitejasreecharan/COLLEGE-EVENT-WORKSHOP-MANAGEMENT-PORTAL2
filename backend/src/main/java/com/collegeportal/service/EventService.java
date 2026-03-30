package com.collegeportal.service;

import com.collegeportal.dto.EventDtos;
import com.collegeportal.entity.EventCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    EventDtos.EventResponse create(EventDtos.EventCreateRequest request, String organizerEmail);
    Page<EventDtos.EventResponse> listPublished(EventCategory category, Pageable pageable);
    void register(Long eventId, String studentEmail);
}
