package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments", indexes = {@Index(name = "idx_department_code", columnList = "code", unique = true)})
public class Department extends BaseEntity {
    @Column(nullable = false, unique = true, length = 20)
    private String code;
    @Column(nullable = false, unique = true, length = 100)
    private String name;
    @Column(length = 500)
    private String description;
    public static class DepartmentBuilder {
        private String code;
        private String name;
        private String description;
        DepartmentBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Department.DepartmentBuilder code(final String code) {
            this.code = code;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Department.DepartmentBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Department.DepartmentBuilder description(final String description) {
            this.description = description;
            return this;
        }
        public Department build() {
            return new Department(this.code, this.name, this.description);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Department.DepartmentBuilder(code=" + this.code + ", name=" + this.name + ", description=" + this.description + ")";
        }
    }
    public static Department.DepartmentBuilder builder() {
        return new Department.DepartmentBuilder();
    }
    public String getCode() {
        return this.code;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public void setCode(final String code) {
        this.code = code;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public Department() {
    }
    public Department(final String code, final String name, final String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }
}

