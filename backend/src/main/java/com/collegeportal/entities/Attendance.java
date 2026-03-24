package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class Attendance extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_id", nullable = false)
    private EventRegistration registration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User attendee;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AttendanceMethod method;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AttendanceStatus status;
    @Column(nullable = false)
    private LocalDateTime checkInTime;
    @Column(nullable = false)
    private Boolean late;
    public static class AttendanceBuilder {
        private EventRegistration registration;
        private Event event;
        private User attendee;
        private AttendanceMethod method;
        private AttendanceStatus status;
        private LocalDateTime checkInTime;
        private Boolean late;
        AttendanceBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Attendance.AttendanceBuilder registration(final EventRegistration registration) {
            this.registration = registration;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Attendance.AttendanceBuilder event(final Event event) {
            this.event = event;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Attendance.AttendanceBuilder attendee(final User attendee) {
            this.attendee = attendee;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Attendance.AttendanceBuilder method(final AttendanceMethod method) {
            this.method = method;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Attendance.AttendanceBuilder status(final AttendanceStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Attendance.AttendanceBuilder checkInTime(final LocalDateTime checkInTime) {
            this.checkInTime = checkInTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Attendance.AttendanceBuilder late(final Boolean late) {
            this.late = late;
            return this;
        }
        public Attendance build() {
            return new Attendance(this.registration, this.event, this.attendee, this.method, this.status, this.checkInTime, this.late);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Attendance.AttendanceBuilder(registration=" + this.registration + ", event=" + this.event + ", attendee=" + this.attendee + ", method=" + this.method + ", status=" + this.status + ", checkInTime=" + this.checkInTime + ", late=" + this.late + ")";
        }
    }
    public static Attendance.AttendanceBuilder builder() {
        return new Attendance.AttendanceBuilder();
    }
    public EventRegistration getRegistration() {
        return this.registration;
    }
    public Event getEvent() {
        return this.event;
    }
    public User getAttendee() {
        return this.attendee;
    }
    public AttendanceMethod getMethod() {
        return this.method;
    }
    public AttendanceStatus getStatus() {
        return this.status;
    }
    public LocalDateTime getCheckInTime() {
        return this.checkInTime;
    }
    public Boolean getLate() {
        return this.late;
    }
    public void setRegistration(final EventRegistration registration) {
        this.registration = registration;
    }
    public void setEvent(final Event event) {
        this.event = event;
    }
    public void setAttendee(final User attendee) {
        this.attendee = attendee;
    }
    public void setMethod(final AttendanceMethod method) {
        this.method = method;
    }
    public void setStatus(final AttendanceStatus status) {
        this.status = status;
    }
    public void setCheckInTime(final LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }
    public void setLate(final Boolean late) {
        this.late = late;
    }
    public Attendance() {
    }
    public Attendance(final EventRegistration registration, final Event event, final User attendee, final AttendanceMethod method, final AttendanceStatus status, final LocalDateTime checkInTime, final Boolean late) {
        this.registration = registration;
        this.event = event;
        this.attendee = attendee;
        this.method = method;
        this.status = status;
        this.checkInTime = checkInTime;
        this.late = late;
    }
}

