package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    private Integer rating;
    @Column(length = 2000)
    private String comment;
    @Column(nullable = false)
    private Boolean approved;
    public static class FeedbackBuilder {
        private Event event;
        private User user;
        private Integer rating;
        private String comment;
        private Boolean approved;
        FeedbackBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Feedback.FeedbackBuilder event(final Event event) {
            this.event = event;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Feedback.FeedbackBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Feedback.FeedbackBuilder rating(final Integer rating) {
            this.rating = rating;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Feedback.FeedbackBuilder comment(final String comment) {
            this.comment = comment;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Feedback.FeedbackBuilder approved(final Boolean approved) {
            this.approved = approved;
            return this;
        }
        public Feedback build() {
            return new Feedback(this.event, this.user, this.rating, this.comment, this.approved);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Feedback.FeedbackBuilder(event=" + this.event + ", user=" + this.user + ", rating=" + this.rating + ", comment=" + this.comment + ", approved=" + this.approved + ")";
        }
    }
    public static Feedback.FeedbackBuilder builder() {
        return new Feedback.FeedbackBuilder();
    }
    public Event getEvent() {
        return this.event;
    }
    public User getUser() {
        return this.user;
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
    public void setEvent(final Event event) {
        this.event = event;
    }
    public void setUser(final User user) {
        this.user = user;
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
    public Feedback() {
    }
    public Feedback(final Event event, final User user, final Integer rating, final String comment, final Boolean approved) {
        this.event = event;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.approved = approved;
    }
}

