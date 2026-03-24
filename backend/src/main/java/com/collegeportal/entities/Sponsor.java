package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "sponsors")
public class Sponsor extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(length = 255)
    private String logoUrl;
    @Column(length = 255)
    private String website;
    @Column(precision = 12, scale = 2)
    private BigDecimal amount;
    public static class SponsorBuilder {
        private Event event;
        private String name;
        private String logoUrl;
        private String website;
        private BigDecimal amount;
        SponsorBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Sponsor.SponsorBuilder event(final Event event) {
            this.event = event;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Sponsor.SponsorBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Sponsor.SponsorBuilder logoUrl(final String logoUrl) {
            this.logoUrl = logoUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Sponsor.SponsorBuilder website(final String website) {
            this.website = website;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Sponsor.SponsorBuilder amount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }
        public Sponsor build() {
            return new Sponsor(this.event, this.name, this.logoUrl, this.website, this.amount);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Sponsor.SponsorBuilder(event=" + this.event + ", name=" + this.name + ", logoUrl=" + this.logoUrl + ", website=" + this.website + ", amount=" + this.amount + ")";
        }
    }
    public static Sponsor.SponsorBuilder builder() {
        return new Sponsor.SponsorBuilder();
    }
    public Event getEvent() {
        return this.event;
    }
    public String getName() {
        return this.name;
    }
    public String getLogoUrl() {
        return this.logoUrl;
    }
    public String getWebsite() {
        return this.website;
    }
    public BigDecimal getAmount() {
        return this.amount;
    }
    public void setEvent(final Event event) {
        this.event = event;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public void setLogoUrl(final String logoUrl) {
        this.logoUrl = logoUrl;
    }
    public void setWebsite(final String website) {
        this.website = website;
    }
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    public Sponsor() {
    }
    public Sponsor(final Event event, final String name, final String logoUrl, final String website, final BigDecimal amount) {
        this.event = event;
        this.name = name;
        this.logoUrl = logoUrl;
        this.website = website;
        this.amount = amount;
    }
}

