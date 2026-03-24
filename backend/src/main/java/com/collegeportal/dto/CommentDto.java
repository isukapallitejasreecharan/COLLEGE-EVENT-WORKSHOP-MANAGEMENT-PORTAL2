package com.collegeportal.dto;

public class CommentDto {
    private Long id;
    private Long eventId;
    private Long parentId;
    private String userName;
    private String content;
    private Integer upvotes;
    private Boolean moderated;
    private String createdAt;
    public Long getId() {
        return this.id;
    }
    public Long getEventId() {
        return this.eventId;
    }
    public Long getParentId() {
        return this.parentId;
    }
    public String getUserName() {
        return this.userName;
    }
    public String getContent() {
        return this.content;
    }
    public Integer getUpvotes() {
        return this.upvotes;
    }
    public Boolean getModerated() {
        return this.moderated;
    }
    public String getCreatedAt() {
        return this.createdAt;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setEventId(final Long eventId) {
        this.eventId = eventId;
    }
    public void setParentId(final Long parentId) {
        this.parentId = parentId;
    }
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    public void setContent(final String content) {
        this.content = content;
    }
    public void setUpvotes(final Integer upvotes) {
        this.upvotes = upvotes;
    }
    public void setModerated(final Boolean moderated) {
        this.moderated = moderated;
    }
    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }
    public CommentDto() {
    }
    public CommentDto(final Long id, final Long eventId, final Long parentId, final String userName, final String content, final Integer upvotes, final Boolean moderated, final String createdAt) {
        this.id = id;
        this.eventId = eventId;
        this.parentId = parentId;
        this.userName = userName;
        this.content = content;
        this.upvotes = upvotes;
        this.moderated = moderated;
        this.createdAt = createdAt;
    }
}

