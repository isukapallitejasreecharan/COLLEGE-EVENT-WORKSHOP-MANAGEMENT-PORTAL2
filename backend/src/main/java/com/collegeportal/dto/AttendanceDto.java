package com.collegeportal.dto;

import com.collegeportal.entities.AttendanceMethod;
import com.collegeportal.entities.AttendanceStatus;
import java.time.LocalDateTime;

public class AttendanceDto {
    private Long id;
    private Long eventId;
    private String eventTitle;
    private Long userId;
    private String attendeeName;
    private AttendanceMethod method;
    private AttendanceStatus status;
    private LocalDateTime checkInTime;
    private Boolean late;
    AttendanceDto(final Long id, final Long eventId, final String eventTitle, final Long userId, final String attendeeName, final AttendanceMethod method, final AttendanceStatus status, final LocalDateTime checkInTime, final Boolean late) {
        this.id = id;
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.userId = userId;
        this.attendeeName = attendeeName;
        this.method = method;
        this.status = status;
        this.checkInTime = checkInTime;
        this.late = late;
    }
    public static class AttendanceDtoBuilder {
        private Long id;
        private Long eventId;
        private String eventTitle;
        private Long userId;
        private String attendeeName;
        private AttendanceMethod method;
        private AttendanceStatus status;
        private LocalDateTime checkInTime;
        private Boolean late;
        AttendanceDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public AttendanceDto.AttendanceDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AttendanceDto.AttendanceDtoBuilder eventId(final Long eventId) {
            this.eventId = eventId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AttendanceDto.AttendanceDtoBuilder eventTitle(final String eventTitle) {
            this.eventTitle = eventTitle;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AttendanceDto.AttendanceDtoBuilder userId(final Long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AttendanceDto.AttendanceDtoBuilder attendeeName(final String attendeeName) {
            this.attendeeName = attendeeName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AttendanceDto.AttendanceDtoBuilder method(final AttendanceMethod method) {
            this.method = method;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AttendanceDto.AttendanceDtoBuilder status(final AttendanceStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AttendanceDto.AttendanceDtoBuilder checkInTime(final LocalDateTime checkInTime) {
            this.checkInTime = checkInTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AttendanceDto.AttendanceDtoBuilder late(final Boolean late) {
            this.late = late;
            return this;
        }
        public AttendanceDto build() {
            return new AttendanceDto(this.id, this.eventId, this.eventTitle, this.userId, this.attendeeName, this.method, this.status, this.checkInTime, this.late);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "AttendanceDto.AttendanceDtoBuilder(id=" + this.id + ", eventId=" + this.eventId + ", eventTitle=" + this.eventTitle + ", userId=" + this.userId + ", attendeeName=" + this.attendeeName + ", method=" + this.method + ", status=" + this.status + ", checkInTime=" + this.checkInTime + ", late=" + this.late + ")";
        }
    }
    public static AttendanceDto.AttendanceDtoBuilder builder() {
        return new AttendanceDto.AttendanceDtoBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public Long getEventId() {
        return this.eventId;
    }
    public String getEventTitle() {
        return this.eventTitle;
    }
    public Long getUserId() {
        return this.userId;
    }
    public String getAttendeeName() {
        return this.attendeeName;
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
}

