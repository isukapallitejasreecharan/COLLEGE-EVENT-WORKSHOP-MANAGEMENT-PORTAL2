package com.collegeportal.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentRequest {
    @NotBlank
    private String content;
    private Long parentId;
    public String getContent() {
        return this.content;
    }
    public Long getParentId() {
        return this.parentId;
    }
    public void setContent(final String content) {
        this.content = content;
    }
    public void setParentId(final Long parentId) {
        this.parentId = parentId;
    }
}

