package com.collegeportal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventSessionDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate sessionDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long speakerId;
    private String speakerName;
    EventSessionDto(final Long id, final String title, final String description, final LocalDate sessionDate, final LocalTime startTime, final LocalTime endTime, final Long speakerId, final String speakerName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.speakerId = speakerId;
        this.speakerName = speakerName;
    }
    public static class EventSessionDtoBuilder {
        private Long id;
        private String title;
        private String description;
        private LocalDate sessionDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private Long speakerId;
        private String speakerName;
        EventSessionDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public EventSessionDto.EventSessionDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSessionDto.EventSessionDtoBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSessionDto.EventSessionDtoBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSessionDto.EventSessionDtoBuilder sessionDate(final LocalDate sessionDate) {
            this.sessionDate = sessionDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSessionDto.EventSessionDtoBuilder startTime(final LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSessionDto.EventSessionDtoBuilder endTime(final LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSessionDto.EventSessionDtoBuilder speakerId(final Long speakerId) {
            this.speakerId = speakerId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSessionDto.EventSessionDtoBuilder speakerName(final String speakerName) {
            this.speakerName = speakerName;
            return this;
        }
        public EventSessionDto build() {
            return new EventSessionDto(this.id, this.title, this.description, this.sessionDate, this.startTime, this.endTime, this.speakerId, this.speakerName);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "EventSessionDto.EventSessionDtoBuilder(id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", sessionDate=" + this.sessionDate + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", speakerId=" + this.speakerId + ", speakerName=" + this.speakerName + ")";
        }
    }
    public static EventSessionDto.EventSessionDtoBuilder builder() {
        return new EventSessionDto.EventSessionDtoBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public LocalDate getSessionDate() {
        return this.sessionDate;
    }
    public LocalTime getStartTime() {
        return this.startTime;
    }
    public LocalTime getEndTime() {
        return this.endTime;
    }
    public Long getSpeakerId() {
        return this.speakerId;
    }
    public String getSpeakerName() {
        return this.speakerName;
    }
}

