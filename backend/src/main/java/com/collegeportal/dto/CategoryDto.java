package com.collegeportal.dto;

public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private String color;
    CategoryDto(final Long id, final String name, final String description, final String color) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
    }
    public static class CategoryDtoBuilder {
        private Long id;
        private String name;
        private String description;
        private String color;
        CategoryDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public CategoryDto.CategoryDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CategoryDto.CategoryDtoBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CategoryDto.CategoryDtoBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CategoryDto.CategoryDtoBuilder color(final String color) {
            this.color = color;
            return this;
        }
        public CategoryDto build() {
            return new CategoryDto(this.id, this.name, this.description, this.color);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "CategoryDto.CategoryDtoBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", color=" + this.color + ")";
        }
    }
    public static CategoryDto.CategoryDtoBuilder builder() {
        return new CategoryDto.CategoryDtoBuilder();
    }
    public Long getId() {
        return this.id;
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
}

