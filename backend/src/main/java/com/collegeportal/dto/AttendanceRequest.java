package com.collegeportal.dto;

import com.collegeportal.entities.AttendanceMethod;
import com.collegeportal.entities.AttendanceStatus;

public class AttendanceRequest {
    private Long registrationId;
    private Long userId;
    private String eventCode;
    private AttendanceMethod method;
    private AttendanceStatus status;
    public Long getRegistrationId() {
        return this.registrationId;
    }
    public Long getUserId() {
        return this.userId;
    }
    public String getEventCode() {
        return this.eventCode;
    }
    public AttendanceMethod getMethod() {
        return this.method;
    }
    public AttendanceStatus getStatus() {
        return this.status;
    }
    public void setRegistrationId(final Long registrationId) {
        this.registrationId = registrationId;
    }
    public void setUserId(final Long userId) {
        this.userId = userId;
    }
    public void setEventCode(final String eventCode) {
        this.eventCode = eventCode;
    }
    public void setMethod(final AttendanceMethod method) {
        this.method = method;
    }
    public void setStatus(final AttendanceStatus status) {
        this.status = status;
    }
}

