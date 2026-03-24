package com.collegeportal.dto;

import java.util.Set;

public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresInSeconds;
    private UserDto user;
    private Set<String> roles;
    AuthResponse(final String accessToken, final String refreshToken, final String tokenType, final Long expiresInSeconds, final UserDto user, final Set<String> roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.expiresInSeconds = expiresInSeconds;
        this.user = user;
        this.roles = roles;
    }
    public static class AuthResponseBuilder {
        private String accessToken;
        private String refreshToken;
        private String tokenType;
        private Long expiresInSeconds;
        private UserDto user;
        private Set<String> roles;
        AuthResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public AuthResponse.AuthResponseBuilder accessToken(final String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuthResponse.AuthResponseBuilder refreshToken(final String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuthResponse.AuthResponseBuilder tokenType(final String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuthResponse.AuthResponseBuilder expiresInSeconds(final Long expiresInSeconds) {
            this.expiresInSeconds = expiresInSeconds;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuthResponse.AuthResponseBuilder user(final UserDto user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuthResponse.AuthResponseBuilder roles(final Set<String> roles) {
            this.roles = roles;
            return this;
        }
        public AuthResponse build() {
            return new AuthResponse(this.accessToken, this.refreshToken, this.tokenType, this.expiresInSeconds, this.user, this.roles);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "AuthResponse.AuthResponseBuilder(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", tokenType=" + this.tokenType + ", expiresInSeconds=" + this.expiresInSeconds + ", user=" + this.user + ", roles=" + this.roles + ")";
        }
    }
    public static AuthResponse.AuthResponseBuilder builder() {
        return new AuthResponse.AuthResponseBuilder();
    }
    public String getAccessToken() {
        return this.accessToken;
    }
    public String getRefreshToken() {
        return this.refreshToken;
    }
    public String getTokenType() {
        return this.tokenType;
    }
    public Long getExpiresInSeconds() {
        return this.expiresInSeconds;
    }
    public UserDto getUser() {
        return this.user;
    }
    public Set<String> getRoles() {
        return this.roles;
    }
}

