package com.collegeportal.dto;

public class VenueDto {
    private Long id;
    private String name;
    private String location;
    private Integer capacity;
    private Boolean indoor;
    private String facilities;
    VenueDto(final Long id, final String name, final String location, final Integer capacity, final Boolean indoor, final String facilities) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.indoor = indoor;
        this.facilities = facilities;
    }
    public static class VenueDtoBuilder {
        private Long id;
        private String name;
        private String location;
        private Integer capacity;
        private Boolean indoor;
        private String facilities;
        VenueDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public VenueDto.VenueDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public VenueDto.VenueDtoBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public VenueDto.VenueDtoBuilder location(final String location) {
            this.location = location;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public VenueDto.VenueDtoBuilder capacity(final Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public VenueDto.VenueDtoBuilder indoor(final Boolean indoor) {
            this.indoor = indoor;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public VenueDto.VenueDtoBuilder facilities(final String facilities) {
            this.facilities = facilities;
            return this;
        }
        public VenueDto build() {
            return new VenueDto(this.id, this.name, this.location, this.capacity, this.indoor, this.facilities);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "VenueDto.VenueDtoBuilder(id=" + this.id + ", name=" + this.name + ", location=" + this.location + ", capacity=" + this.capacity + ", indoor=" + this.indoor + ", facilities=" + this.facilities + ")";
        }
    }
    public static VenueDto.VenueDtoBuilder builder() {
        return new VenueDto.VenueDtoBuilder();
    }
    public Long getId() {
        return this.id;
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
}

