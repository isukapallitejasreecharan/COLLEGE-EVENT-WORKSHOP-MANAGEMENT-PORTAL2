package com.collegeportal.dto;

public class AnnouncementRequest {
    private Long eventId;
    private String title;
    private String content;
    private Boolean active = true;
    public Long getEventId() {
        return this.eventId;
    }
    public String getTitle() {
        return this.title;
    }
    public String getContent() {
        return this.content;
    }
    public Boolean getActive() {
        return this.active;
    }
    public void setEventId(final Long eventId) {
        this.eventId = eventId;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public void setContent(final String content) {
        this.content = content;
    }
    public void setActive(final Boolean active) {
        this.active = active;
    }
}

