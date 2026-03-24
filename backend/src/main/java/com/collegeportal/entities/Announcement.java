package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcements")
public class Announcement extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @Column(nullable = false, length = 180)
    private String title;
    @Column(nullable = false, length = 3000)
    private String content;
    @Column(nullable = false)
    private Boolean active;
    private LocalDateTime publishedAt;
    public static class AnnouncementBuilder {
        private Event event;
        private User author;
        private String title;
        private String content;
        private Boolean active;
        private LocalDateTime publishedAt;
        AnnouncementBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Announcement.AnnouncementBuilder event(final Event event) {
            this.event = event;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Announcement.AnnouncementBuilder author(final User author) {
            this.author = author;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Announcement.AnnouncementBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Announcement.AnnouncementBuilder content(final String content) {
            this.content = content;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Announcement.AnnouncementBuilder active(final Boolean active) {
            this.active = active;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Announcement.AnnouncementBuilder publishedAt(final LocalDateTime publishedAt) {
            this.publishedAt = publishedAt;
            return this;
        }
        public Announcement build() {
            return new Announcement(this.event, this.author, this.title, this.content, this.active, this.publishedAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Announcement.AnnouncementBuilder(event=" + this.event + ", author=" + this.author + ", title=" + this.title + ", content=" + this.content + ", active=" + this.active + ", publishedAt=" + this.publishedAt + ")";
        }
    }
    public static Announcement.AnnouncementBuilder builder() {
        return new Announcement.AnnouncementBuilder();
    }
    public Event getEvent() {
        return this.event;
    }
    public User getAuthor() {
        return this.author;
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
    public LocalDateTime getPublishedAt() {
        return this.publishedAt;
    }
    public void setEvent(final Event event) {
        this.event = event;
    }
    public void setAuthor(final User author) {
        this.author = author;
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
    public void setPublishedAt(final LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
    public Announcement() {
    }
    public Announcement(final Event event, final User author, final String title, final String content, final Boolean active, final LocalDateTime publishedAt) {
        this.event = event;
        this.author = author;
        this.title = title;
        this.content = content;
        this.active = active;
        this.publishedAt = publishedAt;
    }
}

