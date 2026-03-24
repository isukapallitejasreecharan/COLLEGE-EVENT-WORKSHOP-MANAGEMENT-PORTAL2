package com.collegeportal.dto;

import java.time.LocalDateTime;

public class CertificateDto {
    private Long id;
    private String certificateNumber;
    private String verificationCode;
    private String pdfUrl;
    private LocalDateTime issuedAt;
    private String eventTitle;
    private String recipientName;
    private Boolean emailed;
    CertificateDto(final Long id, final String certificateNumber, final String verificationCode, final String pdfUrl, final LocalDateTime issuedAt, final String eventTitle, final String recipientName, final Boolean emailed) {
        this.id = id;
        this.certificateNumber = certificateNumber;
        this.verificationCode = verificationCode;
        this.pdfUrl = pdfUrl;
        this.issuedAt = issuedAt;
        this.eventTitle = eventTitle;
        this.recipientName = recipientName;
        this.emailed = emailed;
    }
    public static class CertificateDtoBuilder {
        private Long id;
        private String certificateNumber;
        private String verificationCode;
        private String pdfUrl;
        private LocalDateTime issuedAt;
        private String eventTitle;
        private String recipientName;
        private Boolean emailed;
        CertificateDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public CertificateDto.CertificateDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CertificateDto.CertificateDtoBuilder certificateNumber(final String certificateNumber) {
            this.certificateNumber = certificateNumber;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CertificateDto.CertificateDtoBuilder verificationCode(final String verificationCode) {
            this.verificationCode = verificationCode;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CertificateDto.CertificateDtoBuilder pdfUrl(final String pdfUrl) {
            this.pdfUrl = pdfUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CertificateDto.CertificateDtoBuilder issuedAt(final LocalDateTime issuedAt) {
            this.issuedAt = issuedAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CertificateDto.CertificateDtoBuilder eventTitle(final String eventTitle) {
            this.eventTitle = eventTitle;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CertificateDto.CertificateDtoBuilder recipientName(final String recipientName) {
            this.recipientName = recipientName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CertificateDto.CertificateDtoBuilder emailed(final Boolean emailed) {
            this.emailed = emailed;
            return this;
        }
        public CertificateDto build() {
            return new CertificateDto(this.id, this.certificateNumber, this.verificationCode, this.pdfUrl, this.issuedAt, this.eventTitle, this.recipientName, this.emailed);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "CertificateDto.CertificateDtoBuilder(id=" + this.id + ", certificateNumber=" + this.certificateNumber + ", verificationCode=" + this.verificationCode + ", pdfUrl=" + this.pdfUrl + ", issuedAt=" + this.issuedAt + ", eventTitle=" + this.eventTitle + ", recipientName=" + this.recipientName + ", emailed=" + this.emailed + ")";
        }
    }
    public static CertificateDto.CertificateDtoBuilder builder() {
        return new CertificateDto.CertificateDtoBuilder();
    }
    public Long getId() {
        return this.id;
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
    public String getEventTitle() {
        return this.eventTitle;
    }
    public String getRecipientName() {
        return this.recipientName;
    }
    public Boolean getEmailed() {
        return this.emailed;
    }
}

