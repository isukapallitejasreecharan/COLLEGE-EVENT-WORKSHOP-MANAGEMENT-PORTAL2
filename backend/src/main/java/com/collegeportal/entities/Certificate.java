package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "certificates", indexes = {@Index(name = "idx_certificate_number", columnList = "certificateNumber", unique = true), @Index(name = "idx_certificate_verification_code", columnList = "verificationCode", unique = true)})
public class Certificate extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false, unique = true, length = 60)
    private String certificateNumber;
    @Column(nullable = false, unique = true, length = 80)
    private String verificationCode;
    @Column(nullable = false, length = 255)
    private String pdfUrl;
    @Column(nullable = false)
    private LocalDateTime issuedAt;
    @Column(nullable = false)
    private Boolean emailed;
    public static class CertificateBuilder {
        private Event event;
        private User user;
        private String certificateNumber;
        private String verificationCode;
        private String pdfUrl;
        private LocalDateTime issuedAt;
        private Boolean emailed;
        CertificateBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Certificate.CertificateBuilder event(final Event event) {
            this.event = event;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Certificate.CertificateBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Certificate.CertificateBuilder certificateNumber(final String certificateNumber) {
            this.certificateNumber = certificateNumber;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Certificate.CertificateBuilder verificationCode(final String verificationCode) {
            this.verificationCode = verificationCode;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Certificate.CertificateBuilder pdfUrl(final String pdfUrl) {
            this.pdfUrl = pdfUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Certificate.CertificateBuilder issuedAt(final LocalDateTime issuedAt) {
            this.issuedAt = issuedAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Certificate.CertificateBuilder emailed(final Boolean emailed) {
            this.emailed = emailed;
            return this;
        }
        public Certificate build() {
            return new Certificate(this.event, this.user, this.certificateNumber, this.verificationCode, this.pdfUrl, this.issuedAt, this.emailed);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Certificate.CertificateBuilder(event=" + this.event + ", user=" + this.user + ", certificateNumber=" + this.certificateNumber + ", verificationCode=" + this.verificationCode + ", pdfUrl=" + this.pdfUrl + ", issuedAt=" + this.issuedAt + ", emailed=" + this.emailed + ")";
        }
    }
    public static Certificate.CertificateBuilder builder() {
        return new Certificate.CertificateBuilder();
    }
    public Event getEvent() {
        return this.event;
    }
    public User getUser() {
        return this.user;
    }
    public String getCertificateNumber() {
        return this.certificateNumber;
    }
    public String getVerificationCode() {
        return this.verificationCode;
    }
    public String getPdfUrl() {
        return this.pdfUrl;
    }
    public LocalDateTime getIssuedAt() {
        return this.issuedAt;
    }
    public Boolean getEmailed() {
        return this.emailed;
    }
    public void setEvent(final Event event) {
        this.event = event;
    }
    public void setUser(final User user) {
        this.user = user;
    }
    public void setCertificateNumber(final String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
    public void setVerificationCode(final String verificationCode) {
        this.verificationCode = verificationCode;
    }
    public void setPdfUrl(final String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
    public void setIssuedAt(final LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }
    public void setEmailed(final Boolean emailed) {
        this.emailed = emailed;
    }
    public Certificate() {
    }
    public Certificate(final Event event, final User user, final String certificateNumber, final String verificationCode, final String pdfUrl, final LocalDateTime issuedAt, final Boolean emailed) {
        this.event = event;
        this.user = user;
        this.certificateNumber = certificateNumber;
        this.verificationCode = verificationCode;
        this.pdfUrl = pdfUrl;
        this.issuedAt = issuedAt;
        this.emailed = emailed;
    }
}

