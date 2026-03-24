package com.collegeportal.dto;

public class DepartmentDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    DepartmentDto(final Long id, final String code, final String name, final String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
    }
    public static class DepartmentDtoBuilder {
        private Long id;
        private String code;
        private String name;
        private String description;
        DepartmentDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public DepartmentDto.DepartmentDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DepartmentDto.DepartmentDtoBuilder code(final String code) {
            this.code = code;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DepartmentDto.DepartmentDtoBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DepartmentDto.DepartmentDtoBuilder description(final String description) {
            this.description = description;
            return this;
        }
        public DepartmentDto build() {
            return new DepartmentDto(this.id, this.code, this.name, this.description);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "DepartmentDto.DepartmentDtoBuilder(id=" + this.id + ", code=" + this.code + ", name=" + this.name + ", description=" + this.description + ")";
        }
    }
    public static DepartmentDto.DepartmentDtoBuilder builder() {
        return new DepartmentDto.DepartmentDtoBuilder();
    }
    public Long getId() {
        return this.id;
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
}

