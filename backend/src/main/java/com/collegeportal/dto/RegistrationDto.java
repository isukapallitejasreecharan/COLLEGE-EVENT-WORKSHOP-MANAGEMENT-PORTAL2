package com.collegeportal.dto;

import com.collegeportal.entities.RegistrationStatus;
import java.time.LocalDateTime;

public class RegistrationDto {
    private Long id;
    private Long eventId;
    private String eventTitle;
    private Long userId;
    private String attendeeName;
    private RegistrationStatus status;
    private Boolean bookmarked;
    private Boolean checkedIn;
    private LocalDateTime registrationDate;
    private String remarks;
    RegistrationDto(final Long id, final Long eventId, final String eventTitle, final Long userId, final String attendeeName, final RegistrationStatus status, final Boolean bookmarked, final Boolean checkedIn, final LocalDateTime registrationDate, final String remarks) {
        this.id = id;
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.userId = userId;
        this.attendeeName = attendeeName;
        this.status = status;
        this.bookmarked = bookmarked;
        this.checkedIn = checkedIn;
        this.registrationDate = registrationDate;
        this.remarks = remarks;
    }
    public static class RegistrationDtoBuilder {
        private Long id;
        private Long eventId;
        private String eventTitle;
        private Long userId;
        private String attendeeName;
        private RegistrationStatus status;
        private Boolean bookmarked;
        private Boolean checkedIn;
        private LocalDateTime registrationDate;
        private String remarks;
        RegistrationDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public RegistrationDto.RegistrationDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RegistrationDto.RegistrationDtoBuilder eventId(final Long eventId) {
            this.eventId = eventId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RegistrationDto.RegistrationDtoBuilder eventTitle(final String eventTitle) {
            this.eventTitle = eventTitle;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RegistrationDto.RegistrationDtoBuilder userId(final Long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RegistrationDto.RegistrationDtoBuilder attendeeName(final String attendeeName) {
            this.attendeeName = attendeeName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RegistrationDto.RegistrationDtoBuilder status(final RegistrationStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RegistrationDto.RegistrationDtoBuilder bookmarked(final Boolean bookmarked) {
            this.bookmarked = bookmarked;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RegistrationDto.RegistrationDtoBuilder checkedIn(final Boolean checkedIn) {
            this.checkedIn = checkedIn;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RegistrationDto.RegistrationDtoBuilder registrationDate(final LocalDateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RegistrationDto.RegistrationDtoBuilder remarks(final String remarks) {
            this.remarks = remarks;
            return this;
        }
        public RegistrationDto build() {
            return new RegistrationDto(this.id, this.eventId, this.eventTitle, this.userId, this.attendeeName, this.status, this.bookmarked, this.checkedIn, this.registrationDate, this.remarks);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "RegistrationDto.RegistrationDtoBuilder(id=" + this.id + ", eventId=" + this.eventId + ", eventTitle=" + this.eventTitle + ", userId=" + this.userId + ", attendeeName=" + this.attendeeName + ", status=" + this.status + ", bookmarked=" + this.bookmarked + ", checkedIn=" + this.checkedIn + ", registrationDate=" + this.registrationDate + ", remarks=" + this.remarks + ")";
        }
    }
    public static RegistrationDto.RegistrationDtoBuilder builder() {
        return new RegistrationDto.RegistrationDtoBuilder();
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
    public String getRemarks() {
        return this.remarks;
    }
}

