package com.collegeportal.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class FeedbackRequest {
    @Min(1)
    @Max(5)
    private Integer rating;
    private String comment;
    public Integer getRating() {
        return this.rating;
    }
    public String getComment() {
        return this.comment;
    }
    public void setRating(final Integer rating) {
        this.rating = rating;
    }
    public void setComment(final String comment) {
        this.comment = comment;
    }
}

