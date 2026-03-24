package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(nullable = false, unique = true, length = 120)
    private String name;
    @Column(length = 500)
    private String description;
    @Column(length = 30)
    private String color;
    public static class CategoryBuilder {
        private String name;
        private String description;
        private String color;
        CategoryBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Category.CategoryBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Category.CategoryBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Category.CategoryBuilder color(final String color) {
            this.color = color;
            return this;
        }
        public Category build() {
            return new Category(this.name, this.description, this.color);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Category.CategoryBuilder(name=" + this.name + ", description=" + this.description + ", color=" + this.color + ")";
        }
    }
    public static Category.CategoryBuilder builder() {
        return new Category.CategoryBuilder();
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public String getColor() {
        return this.color;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public void setColor(final String color) {
        this.color = color;
    }
    public Category() {
    }
    public Category(final String name, final String description, final String color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }
}

