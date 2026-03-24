package com.collegeportal.dto;

public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final T data;
    public static class ApiResponseBuilder<T> {
        private boolean success;
        private String message;
        private T data;
        ApiResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public ApiResponse.ApiResponseBuilder<T> success(final boolean success) {
            this.success = success;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ApiResponse.ApiResponseBuilder<T> message(final String message) {
            this.message = message;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ApiResponse.ApiResponseBuilder<T> data(final T data) {
            this.data = data;
            return this;
        }
        public ApiResponse<T> build() {
            return new ApiResponse<T>(this.success, this.message, this.data);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "ApiResponse.ApiResponseBuilder(success=" + this.success + ", message=" + this.message + ", data=" + this.data + ")";
        }
    }
    public static <T> ApiResponse.ApiResponseBuilder<T> builder() {
        return new ApiResponse.ApiResponseBuilder<T>();
    }
    public boolean isSuccess() {
        return this.success;
    }
    public String getMessage() {
        return this.message;
    }
    public T getData() {
        return this.data;
    }
    public ApiResponse(final boolean success, final String message, final T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}

