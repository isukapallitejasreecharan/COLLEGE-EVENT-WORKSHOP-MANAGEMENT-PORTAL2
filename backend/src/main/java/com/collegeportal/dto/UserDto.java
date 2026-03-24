package com.collegeportal.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phone;
    private String bio;
    private String profileImageUrl;
    private String studentId;
    private String employeeId;
    private String departmentName;
    private Set<String> roles;
    private Boolean enabled;
    private Boolean emailVerified;
    private LocalDateTime lastLoginAt;
    UserDto(final Long id, final String firstName, final String lastName, final String fullName, final String email, final String phone, final String bio, final String profileImageUrl, final String studentId, final String employeeId, final String departmentName, final Set<String> roles, final Boolean enabled, final Boolean emailVerified, final LocalDateTime lastLoginAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
        this.studentId = studentId;
        this.employeeId = employeeId;
        this.departmentName = departmentName;
        this.roles = roles;
        this.enabled = enabled;
        this.emailVerified = emailVerified;
        this.lastLoginAt = lastLoginAt;
    }
    public static class UserDtoBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String fullName;
        private String email;
        private String phone;
        private String bio;
        private String profileImageUrl;
        private String studentId;
        private String employeeId;
        private String departmentName;
        private Set<String> roles;
        private Boolean enabled;
        private Boolean emailVerified;
        private LocalDateTime lastLoginAt;
        UserDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder fullName(final String fullName) {
            this.fullName = fullName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder email(final String email) {
            this.email = email;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder phone(final String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder bio(final String bio) {
            this.bio = bio;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder profileImageUrl(final String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder studentId(final String studentId) {
            this.studentId = studentId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder employeeId(final String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder departmentName(final String departmentName) {
            this.departmentName = departmentName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder roles(final Set<String> roles) {
            this.roles = roles;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder enabled(final Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder emailVerified(final Boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder lastLoginAt(final LocalDateTime lastLoginAt) {
            this.lastLoginAt = lastLoginAt;
            return this;
        }
        public UserDto build() {
            return new UserDto(this.id, this.firstName, this.lastName, this.fullName, this.email, this.phone, this.bio, this.profileImageUrl, this.studentId, this.employeeId, this.departmentName, this.roles, this.enabled, this.emailVerified, this.lastLoginAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "UserDto.UserDtoBuilder(id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", fullName=" + this.fullName + ", email=" + this.email + ", phone=" + this.phone + ", bio=" + this.bio + ", profileImageUrl=" + this.profileImageUrl + ", studentId=" + this.studentId + ", employeeId=" + this.employeeId + ", departmentName=" + this.departmentName + ", roles=" + this.roles + ", enabled=" + this.enabled + ", emailVerified=" + this.emailVerified + ", lastLoginAt=" + this.lastLoginAt + ")";
        }
    }
    public static UserDto.UserDtoBuilder builder() {
        return new UserDto.UserDtoBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getFullName() {
        return this.fullName;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPhone() {
        return this.phone;
    }
    public String getBio() {
        return this.bio;
    }
    public String getProfileImageUrl() {
        return this.profileImageUrl;
    }
    public String getStudentId() {
        return this.studentId;
    }
    public String getEmployeeId() {
        return this.employeeId;
    }
    public String getDepartmentName() {
        return this.departmentName;
    }
    public Set<String> getRoles() {
        return this.roles;
    }
    public Boolean getEnabled() {
        return this.enabled;
    }
    public Boolean getEmailVerified() {
        return this.emailVerified;
    }
    public LocalDateTime getLastLoginAt() {
        return this.lastLoginAt;
    }
}

