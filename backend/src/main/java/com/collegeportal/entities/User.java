package com.collegeportal.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {@Index(name = "idx_user_email", columnList = "email", unique = true), @Index(name = "idx_user_student_id", columnList = "studentId")})
public class User extends BaseEntity {
    @Column(nullable = false, length = 80)
    private String firstName;
    @Column(nullable = false, length = 80)
    private String lastName;
    @Column(nullable = false, unique = true, length = 120)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 30)
    private String phone;
    @Column(length = 1000)
    private String bio;
    @Column(length = 255)
    private String profileImageUrl;
    @Column(length = 40)
    private String studentId;
    @Column(length = 40)
    private String employeeId;
    @Column(nullable = false)
    private Boolean enabled;
    @Column(nullable = false)
    private Boolean emailVerified;
    @Column(nullable = false)
    private Boolean accountNonLocked;
    @Column(nullable = false)
    private Integer failedLoginAttempts;
    private LocalDateTime lockTime;
    private LocalDateTime lastLoginAt;
    @Column(length = 255)
    private String rememberMeToken;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @ElementCollection
    @CollectionTable(name = "user_preferences", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "preference_value", length = 255)
    private Set<String> preferences;

    public String getFullName() {
        return firstName + " " + lastName;
    }
    private static Set<Role> $default$roles() {
        return new HashSet<>();
    }
    private static Set<String> $default$preferences() {
        return new HashSet<>();
    }
    public static class UserBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phone;
        private String bio;
        private String profileImageUrl;
        private String studentId;
        private String employeeId;
        private Boolean enabled;
        private Boolean emailVerified;
        private Boolean accountNonLocked;
        private Integer failedLoginAttempts;
        private LocalDateTime lockTime;
        private LocalDateTime lastLoginAt;
        private String rememberMeToken;
        private Department department;
        private boolean roles$set;
        private Set<Role> roles$value;
        private boolean preferences$set;
        private Set<String> preferences$value;
        UserBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder email(final String email) {
            this.email = email;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder password(final String password) {
            this.password = password;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder phone(final String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder bio(final String bio) {
            this.bio = bio;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder profileImageUrl(final String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder studentId(final String studentId) {
            this.studentId = studentId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder employeeId(final String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder enabled(final Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder emailVerified(final Boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder accountNonLocked(final Boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder failedLoginAttempts(final Integer failedLoginAttempts) {
            this.failedLoginAttempts = failedLoginAttempts;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder lockTime(final LocalDateTime lockTime) {
            this.lockTime = lockTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder lastLoginAt(final LocalDateTime lastLoginAt) {
            this.lastLoginAt = lastLoginAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder rememberMeToken(final String rememberMeToken) {
            this.rememberMeToken = rememberMeToken;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder department(final Department department) {
            this.department = department;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder roles(final Set<Role> roles) {
            this.roles$value = roles;
            roles$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public User.UserBuilder preferences(final Set<String> preferences) {
            this.preferences$value = preferences;
            preferences$set = true;
            return this;
        }
        public User build() {
            Set<Role> roles$value = this.roles$value;
            if (!this.roles$set) roles$value = User.$default$roles();
            Set<String> preferences$value = this.preferences$value;
            if (!this.preferences$set) preferences$value = User.$default$preferences();
            return new User(this.firstName, this.lastName, this.email, this.password, this.phone, this.bio, this.profileImageUrl, this.studentId, this.employeeId, this.enabled, this.emailVerified, this.accountNonLocked, this.failedLoginAttempts, this.lockTime, this.lastLoginAt, this.rememberMeToken, this.department, roles$value, preferences$value);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "User.UserBuilder(firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + ", password=" + this.password + ", phone=" + this.phone + ", bio=" + this.bio + ", profileImageUrl=" + this.profileImageUrl + ", studentId=" + this.studentId + ", employeeId=" + this.employeeId + ", enabled=" + this.enabled + ", emailVerified=" + this.emailVerified + ", accountNonLocked=" + this.accountNonLocked + ", failedLoginAttempts=" + this.failedLoginAttempts + ", lockTime=" + this.lockTime + ", lastLoginAt=" + this.lastLoginAt + ", rememberMeToken=" + this.rememberMeToken + ", department=" + this.department + ", roles$value=" + this.roles$value + ", preferences$value=" + this.preferences$value + ")";
        }
    }
    public static User.UserBuilder builder() {
        return new User.UserBuilder();
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
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
    public Boolean getEnabled() {
        return this.enabled;
    }
    public Boolean getEmailVerified() {
        return this.emailVerified;
    }
    public Boolean getAccountNonLocked() {
        return this.accountNonLocked;
    }
    public Integer getFailedLoginAttempts() {
        return this.failedLoginAttempts;
    }
    public LocalDateTime getLockTime() {
        return this.lockTime;
    }
    public LocalDateTime getLastLoginAt() {
        return this.lastLoginAt;
    }
    public String getRememberMeToken() {
        return this.rememberMeToken;
    }
    public Department getDepartment() {
        return this.department;
    }
    public Set<Role> getRoles() {
        return this.roles;
    }
    public Set<String> getPreferences() {
        return this.preferences;
    }
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    public void setPassword(final String password) {
        this.password = password;
    }
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    public void setBio(final String bio) {
        this.bio = bio;
    }
    public void setProfileImageUrl(final String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
    public void setStudentId(final String studentId) {
        this.studentId = studentId;
    }
    public void setEmployeeId(final String employeeId) {
        this.employeeId = employeeId;
    }
    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }
    public void setEmailVerified(final Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
    public void setAccountNonLocked(final Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public void setFailedLoginAttempts(final Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }
    public void setLockTime(final LocalDateTime lockTime) {
        this.lockTime = lockTime;
    }
    public void setLastLoginAt(final LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
    public void setRememberMeToken(final String rememberMeToken) {
        this.rememberMeToken = rememberMeToken;
    }
    public void setDepartment(final Department department) {
        this.department = department;
    }
    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }
    public void setPreferences(final Set<String> preferences) {
        this.preferences = preferences;
    }
    public User() {
        this.roles = User.$default$roles();
        this.preferences = User.$default$preferences();
    }
    public User(final String firstName, final String lastName, final String email, final String password, final String phone, final String bio, final String profileImageUrl, final String studentId, final String employeeId, final Boolean enabled, final Boolean emailVerified, final Boolean accountNonLocked, final Integer failedLoginAttempts, final LocalDateTime lockTime, final LocalDateTime lastLoginAt, final String rememberMeToken, final Department department, final Set<Role> roles, final Set<String> preferences) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
        this.studentId = studentId;
        this.employeeId = employeeId;
        this.enabled = enabled;
        this.emailVerified = emailVerified;
        this.accountNonLocked = accountNonLocked;
        this.failedLoginAttempts = failedLoginAttempts;
        this.lockTime = lockTime;
        this.lastLoginAt = lastLoginAt;
        this.rememberMeToken = rememberMeToken;
        this.department = department;
        this.roles = roles;
        this.preferences = preferences;
    }
}

