package com.collegeportal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventSessionRequest {
    private Long id;
    private String title;
    private String description;
    private LocalDate sessionDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long speakerId;
    public Long getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public LocalDate getSessionDate() {
        return this.sessionDate;
    }
    public LocalTime getStartTime() {
        return this.startTime;
    }
    public LocalTime getEndTime() {
        return this.endTime;
    }
    public Long getSpeakerId() {
        return this.speakerId;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public void setSessionDate(final LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }
    public void setStartTime(final LocalTime startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(final LocalTime endTime) {
        this.endTime = endTime;
    }
    public void setSpeakerId(final Long speakerId) {
        this.speakerId = speakerId;
    }
}

