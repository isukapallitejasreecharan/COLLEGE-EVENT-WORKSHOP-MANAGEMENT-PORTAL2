package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "venues", indexes = {@Index(name = "idx_venue_name", columnList = "name")})
public class Venue extends BaseEntity {
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 300)
    private String location;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private Boolean indoor;
    @Column(length = 500)
    private String facilities;
    public static class VenueBuilder {
        private String name;
        private String location;
        private Integer capacity;
        private Boolean indoor;
        private String facilities;
        VenueBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Venue.VenueBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Venue.VenueBuilder location(final String location) {
            this.location = location;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Venue.VenueBuilder capacity(final Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Venue.VenueBuilder indoor(final Boolean indoor) {
            this.indoor = indoor;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Venue.VenueBuilder facilities(final String facilities) {
            this.facilities = facilities;
            return this;
        }
        public Venue build() {
            return new Venue(this.name, this.location, this.capacity, this.indoor, this.facilities);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Venue.VenueBuilder(name=" + this.name + ", location=" + this.location + ", capacity=" + this.capacity + ", indoor=" + this.indoor + ", facilities=" + this.facilities + ")";
        }
    }
    public static Venue.VenueBuilder builder() {
        return new Venue.VenueBuilder();
    }
    public String getName() {
        return this.name;
    }
    public String getLocation() {
        return this.location;
    }
    public Integer getCapacity() {
        return this.capacity;
    }
    public Boolean getIndoor() {
        return this.indoor;
    }
    public String getFacilities() {
        return this.facilities;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public void setLocation(final String location) {
        this.location = location;
    }
    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }
    public void setIndoor(final Boolean indoor) {
        this.indoor = indoor;
    }
    public void setFacilities(final String facilities) {
        this.facilities = facilities;
    }
    public Venue() {
    }
    public Venue(final String name, final String location, final Integer capacity, final Boolean indoor, final String facilities) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.indoor = indoor;
        this.facilities = facilities;
    }
}

