package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_volunteers", indexes = {@Index(name = "idx_event_volunteer_unique", columnList = "event_id,volunteer_id", unique = true)})
public class EventVolunteer extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;
    @Column(nullable = false, length = 120)
    private String assignment;
    @Column(nullable = false)
    private Boolean checkedIn;
    public static class EventVolunteerBuilder {
        private Event event;
        private Volunteer volunteer;
        private String assignment;
        private Boolean checkedIn;
        EventVolunteerBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public EventVolunteer.EventVolunteerBuilder event(final Event event) {
            this.event = event;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventVolunteer.EventVolunteerBuilder volunteer(final Volunteer volunteer) {
            this.volunteer = volunteer;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventVolunteer.EventVolunteerBuilder assignment(final String assignment) {
            this.assignment = assignment;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventVolunteer.EventVolunteerBuilder checkedIn(final Boolean checkedIn) {
            this.checkedIn = checkedIn;
            return this;
        }
        public EventVolunteer build() {
            return new EventVolunteer(this.event, this.volunteer, this.assignment, this.checkedIn);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "EventVolunteer.EventVolunteerBuilder(event=" + this.event + ", volunteer=" + this.volunteer + ", assignment=" + this.assignment + ", checkedIn=" + this.checkedIn + ")";
        }
    }
    public static EventVolunteer.EventVolunteerBuilder builder() {
        return new EventVolunteer.EventVolunteerBuilder();
    }
    public Event getEvent() {
        return this.event;
    }
    public Volunteer getVolunteer() {
        return this.volunteer;
    }
    public String getAssignment() {
        return this.assignment;
    }
    public Boolean getCheckedIn() {
        return this.checkedIn;
    }
    public void setEvent(final Event event) {
        this.event = event;
    }
    public void setVolunteer(final Volunteer volunteer) {
        this.volunteer = volunteer;
    }
    public void setAssignment(final String assignment) {
        this.assignment = assignment;
    }
    public void setCheckedIn(final Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
    public EventVolunteer() {
    }
    public EventVolunteer(final Event event, final Volunteer volunteer, final String assignment, final Boolean checkedIn) {
        this.event = event;
        this.volunteer = volunteer;
        this.assignment = assignment;
        this.checkedIn = checkedIn;
    }
}

