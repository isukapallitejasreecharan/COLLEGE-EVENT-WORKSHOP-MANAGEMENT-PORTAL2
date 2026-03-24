package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_tokens")
public class VerificationToken extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false, unique = true, length = 200)
    private String token;
    @Column(nullable = false)
    private LocalDateTime expiryDate;
    @Column(nullable = false)
    private Boolean used;
    public static class VerificationTokenBuilder {
        private User user;
        private String token;
        private LocalDateTime expiryDate;
        private Boolean used;
        VerificationTokenBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public VerificationToken.VerificationTokenBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public VerificationToken.VerificationTokenBuilder token(final String token) {
            this.token = token;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public VerificationToken.VerificationTokenBuilder expiryDate(final LocalDateTime expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public VerificationToken.VerificationTokenBuilder used(final Boolean used) {
            this.used = used;
            return this;
        }
        public VerificationToken build() {
            return new VerificationToken(this.user, this.token, this.expiryDate, this.used);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "VerificationToken.VerificationTokenBuilder(user=" + this.user + ", token=" + this.token + ", expiryDate=" + this.expiryDate + ", used=" + this.used + ")";
        }
    }
    public static VerificationToken.VerificationTokenBuilder builder() {
        return new VerificationToken.VerificationTokenBuilder();
    }
    public User getUser() {
        return this.user;
    }
    public String getToken() {
        return this.token;
    }
    public LocalDateTime getExpiryDate() {
        return this.expiryDate;
    }
    public Boolean getUsed() {
        return this.used;
    }
    public void setUser(final User user) {
        this.user = user;
    }
    public void setToken(final String token) {
        this.token = token;
    }
    public void setExpiryDate(final LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
    public void setUsed(final Boolean used) {
        this.used = used;
    }
    public VerificationToken() {
    }
    public VerificationToken(final User user, final String token, final LocalDateTime expiryDate, final Boolean used) {
        this.user = user;
        this.token = token;
        this.expiryDate = expiryDate;
        this.used = used;
    }
}

