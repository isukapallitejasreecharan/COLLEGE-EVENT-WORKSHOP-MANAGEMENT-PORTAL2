package com.collegeportal.dto;

import java.util.List;

public class PageResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
    PageResponse(final List<T> content, final int page, final int size, final long totalElements, final int totalPages, final boolean last) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }
    public static class PageResponseBuilder<T> {
        private List<T> content;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean last;
        PageResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public PageResponse.PageResponseBuilder<T> content(final List<T> content) {
            this.content = content;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PageResponse.PageResponseBuilder<T> page(final int page) {
            this.page = page;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PageResponse.PageResponseBuilder<T> size(final int size) {
            this.size = size;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PageResponse.PageResponseBuilder<T> totalElements(final long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PageResponse.PageResponseBuilder<T> totalPages(final int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PageResponse.PageResponseBuilder<T> last(final boolean last) {
            this.last = last;
            return this;
        }
        public PageResponse<T> build() {
            return new PageResponse<T>(this.content, this.page, this.size, this.totalElements, this.totalPages, this.last);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "PageResponse.PageResponseBuilder(content=" + this.content + ", page=" + this.page + ", size=" + this.size + ", totalElements=" + this.totalElements + ", totalPages=" + this.totalPages + ", last=" + this.last + ")";
        }
    }
    public static <T> PageResponse.PageResponseBuilder<T> builder() {
        return new PageResponse.PageResponseBuilder<T>();
    }
    public List<T> getContent() {
        return this.content;
    }
    public int getPage() {
        return this.page;
    }
    public int getSize() {
        return this.size;
    }
    public long getTotalElements() {
        return this.totalElements;
    }
    public int getTotalPages() {
        return this.totalPages;
    }
    public boolean isLast() {
        return this.last;
    }
}

