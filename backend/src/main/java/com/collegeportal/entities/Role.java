package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 50)
    private RoleName name;
    @Column(nullable = false)
    private String description;
    public static class RoleBuilder {
        private RoleName name;
        private String description;
        RoleBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Role.RoleBuilder name(final RoleName name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Role.RoleBuilder description(final String description) {
            this.description = description;
            return this;
        }
        public Role build() {
            return new Role(this.name, this.description);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Role.RoleBuilder(name=" + this.name + ", description=" + this.description + ")";
        }
    }
    public static Role.RoleBuilder builder() {
        return new Role.RoleBuilder();
    }
    public RoleName getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public void setName(final RoleName name) {
        this.name = name;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public Role() {
    }
    public Role(final RoleName name, final String description) {
        this.name = name;
        this.description = description;
    }
}

