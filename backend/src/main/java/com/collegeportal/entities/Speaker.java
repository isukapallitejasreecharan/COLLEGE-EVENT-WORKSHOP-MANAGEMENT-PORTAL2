package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "speakers")
public class Speaker extends BaseEntity {
    @Column(nullable = false, length = 120)
    private String name;
    @Column(length = 120)
    private String designation;
    @Column(length = 120)
    private String organization;
    @Column(length = 120)
    private String email;
    @Column(length = 255)
    private String avatarUrl;
    @Column(length = 1500)
    private String bio;
    public static class SpeakerBuilder {
        private String name;
        private String designation;
        private String organization;
        private String email;
        private String avatarUrl;
        private String bio;
        SpeakerBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Speaker.SpeakerBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Speaker.SpeakerBuilder designation(final String designation) {
            this.designation = designation;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Speaker.SpeakerBuilder organization(final String organization) {
            this.organization = organization;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Speaker.SpeakerBuilder email(final String email) {
            this.email = email;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Speaker.SpeakerBuilder avatarUrl(final String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Speaker.SpeakerBuilder bio(final String bio) {
            this.bio = bio;
            return this;
        }
        public Speaker build() {
            return new Speaker(this.name, this.designation, this.organization, this.email, this.avatarUrl, this.bio);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Speaker.SpeakerBuilder(name=" + this.name + ", designation=" + this.designation + ", organization=" + this.organization + ", email=" + this.email + ", avatarUrl=" + this.avatarUrl + ", bio=" + this.bio + ")";
        }
    }
    public static Speaker.SpeakerBuilder builder() {
        return new Speaker.SpeakerBuilder();
    }
    public String getName() {
        return this.name;
    }
    public String getDesignation() {
        return this.designation;
    }
    public String getOrganization() {
        return this.organization;
    }
    public String getEmail() {
        return this.email;
    }
    public String getAvatarUrl() {
        return this.avatarUrl;
    }
    public String getBio() {
        return this.bio;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public void setDesignation(final String designation) {
        this.designation = designation;
    }
    public void setOrganization(final String organization) {
        this.organization = organization;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    public void setAvatarUrl(final String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public void setBio(final String bio) {
        this.bio = bio;
    }
    public Speaker() {
    }
    public Speaker(final String name, final String designation, final String organization, final String email, final String avatarUrl, final String bio) {
        this.name = name;
        this.designation = designation;
        this.organization = organization;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.bio = bio;
    }
}

