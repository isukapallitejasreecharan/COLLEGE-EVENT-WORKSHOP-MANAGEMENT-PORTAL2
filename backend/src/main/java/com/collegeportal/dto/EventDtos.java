package com.collegeportal.dto;

import com.collegeportal.entity.EventCategory;
import com.collegeportal.entity.EventStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventDtos {
    public record EventCreateRequest(
            @NotBlank String title,
            String description,
            @NotNull EventCategory category,
            String tags,
            String eventType,
            @NotNull LocalDate date,
            @NotNull LocalTime startTime,
            @NotNull LocalTime endTime,
            @NotNull Integer capacity,
            @NotNull Long venueId,
            @NotNull LocalDateTime registrationDeadline,
            String eventPoster
    ) {}

    public record EventResponse(
            Long id,
            String title,
            String description,
            EventCategory category,
            String eventType,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime,
            Integer capacity,
            EventStatus status,
            String venueName,
            String organizerName
    ) {}
}
