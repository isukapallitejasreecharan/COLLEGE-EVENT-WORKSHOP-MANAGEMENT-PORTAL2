package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_registrations", indexes = {@Index(name = "idx_registration_event_user", columnList = "event_id,user_id", unique = true), @Index(name = "idx_registration_status", columnList = "status")})
public class EventRegistration extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private RegistrationStatus status;
    @Column(nullable = false)
    private Boolean bookmarked;
    @Column(nullable = false)
    private Boolean checkedIn;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    private LocalDateTime cancelledAt;
    @Column(length = 500)
    private String remarks;
    public static class EventRegistrationBuilder {
        private Event event;
        private User user;
        private RegistrationStatus status;
        private Boolean bookmarked;
        private Boolean checkedIn;
        private LocalDateTime registrationDate;
        private LocalDateTime cancelledAt;
        private String remarks;
        EventRegistrationBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public EventRegistration.EventRegistrationBuilder event(final Event event) {
            this.event = event;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventRegistration.EventRegistrationBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventRegistration.EventRegistrationBuilder status(final RegistrationStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventRegistration.EventRegistrationBuilder bookmarked(final Boolean bookmarked) {
            this.bookmarked = bookmarked;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventRegistration.EventRegistrationBuilder checkedIn(final Boolean checkedIn) {
            this.checkedIn = checkedIn;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventRegistration.EventRegistrationBuilder registrationDate(final LocalDateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventRegistration.EventRegistrationBuilder cancelledAt(final LocalDateTime cancelledAt) {
            this.cancelledAt = cancelledAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventRegistration.EventRegistrationBuilder remarks(final String remarks) {
            this.remarks = remarks;
            return this;
        }
        public EventRegistration build() {
            return new EventRegistration(this.event, this.user, this.status, this.bookmarked, this.checkedIn, this.registrationDate, this.cancelledAt, this.remarks);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "EventRegistration.EventRegistrationBuilder(event=" + this.event + ", user=" + this.user + ", status=" + this.status + ", bookmarked=" + this.bookmarked + ", checkedIn=" + this.checkedIn + ", registrationDate=" + this.registrationDate + ", cancelledAt=" + this.cancelledAt + ", remarks=" + this.remarks + ")";
        }
    }
    public static EventRegistration.EventRegistrationBuilder builder() {
        return new EventRegistration.EventRegistrationBuilder();
    }
    public Event getEvent() {
        return this.event;
    }
    public User getUser() {
        return this.user;
    }
    public RegistrationStatus getStatus() {
        return this.status;
    }
    public Boolean getBookmarked() {
        return this.bookmarked;
    }
    public Boolean getCheckedIn() {
        return this.checkedIn;
    }
    public LocalDateTime getRegistrationDate() {
        return this.registrationDate;
    }
    public LocalDateTime getCancelledAt() {
        return this.cancelledAt;
    }
    public String getRemarks() {
        return this.remarks;
    }
    public void setEvent(final Event event) {
        this.event = event;
    }
    public void setUser(final User user) {
        this.user = user;
    }
    public void setStatus(final RegistrationStatus status) {
        this.status = status;
    }
    public void setBookmarked(final Boolean bookmarked) {
        this.bookmarked = bookmarked;
    }
    public void setCheckedIn(final Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
    public void setRegistrationDate(final LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    public void setCancelledAt(final LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }
    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }
    public EventRegistration() {
    }
    public EventRegistration(final Event event, final User user, final RegistrationStatus status, final Boolean bookmarked, final Boolean checkedIn, final LocalDateTime registrationDate, final LocalDateTime cancelledAt, final String remarks) {
        this.event = event;
        this.user = user;
        this.status = status;
        this.bookmarked = bookmarked;
        this.checkedIn = checkedIn;
        this.registrationDate = registrationDate;
        this.cancelledAt = cancelledAt;
        this.remarks = remarks;
    }
}

