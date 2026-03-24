package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_logs")
public class AuditLog extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false, length = 120)
    private String action;
    @Column(nullable = false, length = 120)
    private String entityType;
    @Column(length = 60)
    private String entityId;
    @Column(length = 2000)
    private String details;
    public static class AuditLogBuilder {
        private User user;
        private String action;
        private String entityType;
        private String entityId;
        private String details;
        AuditLogBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder action(final String action) {
            this.action = action;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder entityType(final String entityType) {
            this.entityType = entityType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder entityId(final String entityId) {
            this.entityId = entityId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder details(final String details) {
            this.details = details;
            return this;
        }
        public AuditLog build() {
            return new AuditLog(this.user, this.action, this.entityType, this.entityId, this.details);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "AuditLog.AuditLogBuilder(user=" + this.user + ", action=" + this.action + ", entityType=" + this.entityType + ", entityId=" + this.entityId + ", details=" + this.details + ")";
        }
    }
    public static AuditLog.AuditLogBuilder builder() {
        return new AuditLog.AuditLogBuilder();
    }
    public User getUser() {
        return this.user;
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
    public void setUser(final User user) {
        this.user = user;
    }
    public void setAction(final String action) {
        this.action = action;
    }
    public void setEntityType(final String entityType) {
        this.entityType = entityType;
    }
    public void setEntityId(final String entityId) {
        this.entityId = entityId;
    }
    public void setDetails(final String details) {
        this.details = details;
    }
    public AuditLog() {
    }
    public AuditLog(final User user, final String action, final String entityType, final String entityId, final String details) {
        this.user = user;
        this.action = action;
        this.entityType = entityType;
        this.entityId = entityId;
        this.details = details;
    }
}

