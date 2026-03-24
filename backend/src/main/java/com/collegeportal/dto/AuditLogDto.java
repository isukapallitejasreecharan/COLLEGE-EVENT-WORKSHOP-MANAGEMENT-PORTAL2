package com.collegeportal.dto;

import java.time.LocalDateTime;

public class AuditLogDto {
    private Long id;
    private Long userId;
    private String userName;
    private String action;
    private String entityType;
    private String entityId;
    private String details;
    private LocalDateTime createdAt;
    AuditLogDto(final Long id, final Long userId, final String userName, final String action, final String entityType, final String entityId, final String details, final LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.action = action;
        this.entityType = entityType;
        this.entityId = entityId;
        this.details = details;
        this.createdAt = createdAt;
    }
    public static class AuditLogDtoBuilder {
        private Long id;
        private Long userId;
        private String userName;
        private String action;
        private String entityType;
        private String entityId;
        private String details;
        private LocalDateTime createdAt;
        AuditLogDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public AuditLogDto.AuditLogDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLogDto.AuditLogDtoBuilder userId(final Long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLogDto.AuditLogDtoBuilder userName(final String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLogDto.AuditLogDtoBuilder action(final String action) {
            this.action = action;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLogDto.AuditLogDtoBuilder entityType(final String entityType) {
            this.entityType = entityType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLogDto.AuditLogDtoBuilder entityId(final String entityId) {
            this.entityId = entityId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLogDto.AuditLogDtoBuilder details(final String details) {
            this.details = details;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLogDto.AuditLogDtoBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public AuditLogDto build() {
            return new AuditLogDto(this.id, this.userId, this.userName, this.action, this.entityType, this.entityId, this.details, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "AuditLogDto.AuditLogDtoBuilder(id=" + this.id + ", userId=" + this.userId + ", userName=" + this.userName + ", action=" + this.action + ", entityType=" + this.entityType + ", entityId=" + this.entityId + ", details=" + this.details + ", createdAt=" + this.createdAt + ")";
        }
    }
    public static AuditLogDto.AuditLogDtoBuilder builder() {
        return new AuditLogDto.AuditLogDtoBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public String getAction() {
        return this.action;
    }
    public String getEntityType() {
        return this.entityType;
    }
    public String getEntityId() {
        return this.entityId;
    }
    public String getDetails() {
        return this.details;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}

