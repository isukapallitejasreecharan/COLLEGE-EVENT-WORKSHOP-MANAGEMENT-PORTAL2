package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false, length = 1000)
    private String message;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private NotificationType type;
    @Column(nullable = false)
    private Boolean isRead;
    @Column(length = 255)
    private String linkUrl;
    public static class NotificationBuilder {
        private User user;
        private String title;
        private String message;
        private NotificationType type;
        private Boolean isRead;
        private String linkUrl;
        NotificationBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder message(final String message) {
            this.message = message;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder type(final NotificationType type) {
            this.type = type;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder isRead(final Boolean isRead) {
            this.isRead = isRead;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder linkUrl(final String linkUrl) {
            this.linkUrl = linkUrl;
            return this;
        }
        public Notification build() {
            return new Notification(this.user, this.title, this.message, this.type, this.isRead, this.linkUrl);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Notification.NotificationBuilder(user=" + this.user + ", title=" + this.title + ", message=" + this.message + ", type=" + this.type + ", isRead=" + this.isRead + ", linkUrl=" + this.linkUrl + ")";
        }
    }
    public static Notification.NotificationBuilder builder() {
        return new Notification.NotificationBuilder();
    }
    public User getUser() {
        return this.user;
    }
    public String getTitle() {
        return this.title;
    }
    public String getMessage() {
        return this.message;
    }
    public NotificationType getType() {
        return this.type;
    }
    public Boolean getIsRead() {
        return this.isRead;
    }
    public String getLinkUrl() {
        return this.linkUrl;
    }
    public void setUser(final User user) {
        this.user = user;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public void setMessage(final String message) {
        this.message = message;
    }
    public void setType(final NotificationType type) {
        this.type = type;
    }
    public void setIsRead(final Boolean isRead) {
        this.isRead = isRead;
    }
    public void setLinkUrl(final String linkUrl) {
        this.linkUrl = linkUrl;
    }
    public Notification() {
    }
    public Notification(final User user, final String title, final String message, final NotificationType type, final Boolean isRead, final String linkUrl) {
        this.user = user;
        this.title = title;
        this.message = message;
        this.type = type;
        this.isRead = isRead;
        this.linkUrl = linkUrl;
    }
}

