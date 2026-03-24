package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "event_sessions")
public class EventSession extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @Column(nullable = false, length = 150)
    private String title;
    @Column(length = 2000)
    private String description;
    @Column(nullable = false)
    private LocalDate sessionDate;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;
    @Column(length = 255)
    private String materialUrl;
    public static class EventSessionBuilder {
        private Event event;
        private String title;
        private String description;
        private LocalDate sessionDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private Speaker speaker;
        private String materialUrl;
        EventSessionBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public EventSession.EventSessionBuilder event(final Event event) {
            this.event = event;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSession.EventSessionBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSession.EventSessionBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSession.EventSessionBuilder sessionDate(final LocalDate sessionDate) {
            this.sessionDate = sessionDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSession.EventSessionBuilder startTime(final LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSession.EventSessionBuilder endTime(final LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSession.EventSessionBuilder speaker(final Speaker speaker) {
            this.speaker = speaker;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventSession.EventSessionBuilder materialUrl(final String materialUrl) {
            this.materialUrl = materialUrl;
            return this;
        }
        public EventSession build() {
            return new EventSession(this.event, this.title, this.description, this.sessionDate, this.startTime, this.endTime, this.speaker, this.materialUrl);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "EventSession.EventSessionBuilder(event=" + this.event + ", title=" + this.title + ", description=" + this.description + ", sessionDate=" + this.sessionDate + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", speaker=" + this.speaker + ", materialUrl=" + this.materialUrl + ")";
        }
    }
    public static EventSession.EventSessionBuilder builder() {
        return new EventSession.EventSessionBuilder();
    }
    public Event getEvent() {
        return this.event;
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
    public Speaker getSpeaker() {
        return this.speaker;
    }
    public String getMaterialUrl() {
        return this.materialUrl;
    }
    public void setEvent(final Event event) {
        this.event = event;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public void setSessionDate(final LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }
    public void setStartTime(final LocalTime startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(final LocalTime endTime) {
        this.endTime = endTime;
    }
    public void setSpeaker(final Speaker speaker) {
        this.speaker = speaker;
    }
    public void setMaterialUrl(final String materialUrl) {
        this.materialUrl = materialUrl;
    }
    public EventSession() {
    }
    public EventSession(final Event event, final String title, final String description, final LocalDate sessionDate, final LocalTime startTime, final LocalTime endTime, final Speaker speaker, final String materialUrl) {
        this.event = event;
        this.title = title;
        this.description = description;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.speaker = speaker;
        this.materialUrl = materialUrl;
    }
}

