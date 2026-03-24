package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false, unique = true, length = 300)
    private String token;
    @Column(nullable = false)
    private LocalDateTime expiryDate;
    @Column(nullable = false)
    private Boolean revoked;
    public static class RefreshTokenBuilder {
        private User user;
        private String token;
        private LocalDateTime expiryDate;
        private Boolean revoked;
        RefreshTokenBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public RefreshToken.RefreshTokenBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RefreshToken.RefreshTokenBuilder token(final String token) {
            this.token = token;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RefreshToken.RefreshTokenBuilder expiryDate(final LocalDateTime expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RefreshToken.RefreshTokenBuilder revoked(final Boolean revoked) {
            this.revoked = revoked;
            return this;
        }
        public RefreshToken build() {
            return new RefreshToken(this.user, this.token, this.expiryDate, this.revoked);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "RefreshToken.RefreshTokenBuilder(user=" + this.user + ", token=" + this.token + ", expiryDate=" + this.expiryDate + ", revoked=" + this.revoked + ")";
        }
    }
    public static RefreshToken.RefreshTokenBuilder builder() {
        return new RefreshToken.RefreshTokenBuilder();
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
    public Boolean getRevoked() {
        return this.revoked;
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
    public void setRevoked(final Boolean revoked) {
        this.revoked = revoked;
    }
    public RefreshToken() {
    }
    public RefreshToken(final User user, final String token, final LocalDateTime expiryDate, final Boolean revoked) {
        this.user = user;
        this.token = token;
        this.expiryDate = expiryDate;
        this.revoked = revoked;
    }
}

