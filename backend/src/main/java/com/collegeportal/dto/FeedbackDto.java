package com.collegeportal.dto;

public class FeedbackDto {
    private Long id;
    private Long eventId;
    private String eventTitle;
    private String userName;
    private Integer rating;
    private String comment;
    private Boolean approved;
    public Long getId() {
        return this.id;
    }
    public Long getEventId() {
        return this.eventId;
    }
    public String getEventTitle() {
        return this.eventTitle;
    }
    public String getUserName() {
        return this.userName;
    }
    public Integer getRating() {
        return this.rating;
    }
    public String getComment() {
        return this.comment;
    }
    public Boolean getApproved() {
        return this.approved;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setEventId(final Long eventId) {
        this.eventId = eventId;
    }
    public void setEventTitle(final String eventTitle) {
        this.eventTitle = eventTitle;
    }
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    public void setRating(final Integer rating) {
        this.rating = rating;
    }
    public void setComment(final String comment) {
        this.comment = comment;
    }
    public void setApproved(final Boolean approved) {
        this.approved = approved;
    }
    public FeedbackDto() {
    }
    public FeedbackDto(final Long id, final Long eventId, final String eventTitle, final String userName, final Integer rating, final String comment, final Boolean approved) {
        this.id = id;
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.userName = userName;
        this.rating = rating;
        this.comment = comment;
        this.approved = approved;
    }
}

