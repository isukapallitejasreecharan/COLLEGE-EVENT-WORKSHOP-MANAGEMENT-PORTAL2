package com.collegeportal.dto;

import com.collegeportal.entities.NotificationType;
import java.time.LocalDateTime;

public class NotificationDto {
    private Long id;
    private String title;
    private String message;
    private NotificationType type;
    private Boolean isRead;
    private String linkUrl;
    private LocalDateTime createdAt;
    NotificationDto(final Long id, final String title, final String message, final NotificationType type, final Boolean isRead, final String linkUrl, final LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.type = type;
        this.isRead = isRead;
        this.linkUrl = linkUrl;
        this.createdAt = createdAt;
    }
    public static class NotificationDtoBuilder {
        private Long id;
        private String title;
        private String message;
        private NotificationType type;
        private Boolean isRead;
        private String linkUrl;
        private LocalDateTime createdAt;
        NotificationDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder message(final String message) {
            this.message = message;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder type(final NotificationType type) {
            this.type = type;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder isRead(final Boolean isRead) {
            this.isRead = isRead;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder linkUrl(final String linkUrl) {
            this.linkUrl = linkUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public NotificationDto build() {
            return new NotificationDto(this.id, this.title, this.message, this.type, this.isRead, this.linkUrl, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "NotificationDto.NotificationDtoBuilder(id=" + this.id + ", title=" + this.title + ", message=" + this.message + ", type=" + this.type + ", isRead=" + this.isRead + ", linkUrl=" + this.linkUrl + ", createdAt=" + this.createdAt + ")";
        }
    }
    public static NotificationDto.NotificationDtoBuilder builder() {
        return new NotificationDto.NotificationDtoBuilder();
    }
    public Long getId() {
        return this.id;
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
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}

