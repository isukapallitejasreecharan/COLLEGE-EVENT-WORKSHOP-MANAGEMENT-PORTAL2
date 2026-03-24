package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "volunteers")
public class Volunteer extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    @Column(length = 500)
    private String skills;
    @Column(length = 500)
    private String availability;
    @Column(length = 1000)
    private String notes;
    public static class VolunteerBuilder {
        private User user;
        private String skills;
        private String availability;
        private String notes;
        VolunteerBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Volunteer.VolunteerBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Volunteer.VolunteerBuilder skills(final String skills) {
            this.skills = skills;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Volunteer.VolunteerBuilder availability(final String availability) {
            this.availability = availability;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Volunteer.VolunteerBuilder notes(final String notes) {
            this.notes = notes;
            return this;
        }
        public Volunteer build() {
            return new Volunteer(this.user, this.skills, this.availability, this.notes);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Volunteer.VolunteerBuilder(user=" + this.user + ", skills=" + this.skills + ", availability=" + this.availability + ", notes=" + this.notes + ")";
        }
    }
    public static Volunteer.VolunteerBuilder builder() {
        return new Volunteer.VolunteerBuilder();
    }
    public User getUser() {
        return this.user;
    }
    public String getSkills() {
        return this.skills;
    }
    public String getAvailability() {
        return this.availability;
    }
    public String getNotes() {
        return this.notes;
    }
    public void setUser(final User user) {
        this.user = user;
    }
    public void setSkills(final String skills) {
        this.skills = skills;
    }
    public void setAvailability(final String availability) {
        this.availability = availability;
    }
    public void setNotes(final String notes) {
        this.notes = notes;
    }
    public Volunteer() {
    }
    public Volunteer(final User user, final String skills, final String availability, final String notes) {
        this.user = user;
        this.skills = skills;
        this.availability = availability;
        this.notes = notes;
    }
}

