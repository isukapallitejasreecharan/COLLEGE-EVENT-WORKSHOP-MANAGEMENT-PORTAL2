package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;
    @Column(nullable = false, length = 2000)
    private String content;
    @Column(nullable = false)
    private Integer upvotes;
    @Column(nullable = false)
    private Boolean moderated;
    public static class CommentBuilder {
        private Event event;
        private User user;
        private Comment parent;
        private String content;
        private Integer upvotes;
        private Boolean moderated;
        CommentBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Comment.CommentBuilder event(final Event event) {
            this.event = event;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Comment.CommentBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Comment.CommentBuilder parent(final Comment parent) {
            this.parent = parent;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Comment.CommentBuilder content(final String content) {
            this.content = content;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Comment.CommentBuilder upvotes(final Integer upvotes) {
            this.upvotes = upvotes;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Comment.CommentBuilder moderated(final Boolean moderated) {
            this.moderated = moderated;
            return this;
        }
        public Comment build() {
            return new Comment(this.event, this.user, this.parent, this.content, this.upvotes, this.moderated);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Comment.CommentBuilder(event=" + this.event + ", user=" + this.user + ", parent=" + this.parent + ", content=" + this.content + ", upvotes=" + this.upvotes + ", moderated=" + this.moderated + ")";
        }
    }
    public static Comment.CommentBuilder builder() {
        return new Comment.CommentBuilder();
    }
    public Event getEvent() {
        return this.event;
    }
    public User getUser() {
        return this.user;
    }
    public Comment getParent() {
        return this.parent;
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
    public void setEvent(final Event event) {
        this.event = event;
    }
    public void setUser(final User user) {
        this.user = user;
    }
    public void setParent(final Comment parent) {
        this.parent = parent;
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
    public Comment() {
    }
    public Comment(final Event event, final User user, final Comment parent, final String content, final Integer upvotes, final Boolean moderated) {
        this.event = event;
        this.user = user;
        this.parent = parent;
        this.content = content;
        this.upvotes = upvotes;
        this.moderated = moderated;
    }
}

