package com.collegeportal.dto;

import java.time.LocalDateTime;

public class AnnouncementDto {
    private Long id;
    private Long eventId;
    private String title;
    private String content;
    private String authorName;
    private Boolean active;
    private LocalDateTime publishedAt;
    public Long getId() {
        return this.id;
    }
    public Long getEventId() {
        return this.eventId;
    }
    public String getTitle() {
        return this.title;
    }
    public String getContent() {
        return this.content;
    }
    public String getAuthorName() {
        return this.authorName;
    }
    public Boolean getActive() {
        return this.active;
    }
    public LocalDateTime getPublishedAt() {
        return this.publishedAt;
    }
    public void setId(final Long id) {
        this.id = id;
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
    public void setAuthorName(final String authorName) {
        this.authorName = authorName;
    }
    public void setActive(final Boolean active) {
        this.active = active;
    }
    public void setPublishedAt(final LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
    public AnnouncementDto() {
    }
    public AnnouncementDto(final Long id, final Long eventId, final String title, final String content, final String authorName, final Boolean active, final LocalDateTime publishedAt) {
        this.id = id;
        this.eventId = eventId;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.active = active;
        this.publishedAt = publishedAt;
    }
}

