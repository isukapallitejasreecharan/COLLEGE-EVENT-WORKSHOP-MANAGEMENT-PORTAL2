package com.collegeportal.dto;

import jakarta.validation.constraints.NotBlank;

public class VolunteerAssignmentRequest {
    private Long volunteerId;
    @NotBlank
    private String assignment;
    public Long getVolunteerId() {
        return this.volunteerId;
    }
    public String getAssignment() {
        return this.assignment;
    }
    public void setVolunteerId(final Long volunteerId) {
        this.volunteerId = volunteerId;
    }
    public void setAssignment(final String assignment) {
        this.assignment = assignment;
    }
}

