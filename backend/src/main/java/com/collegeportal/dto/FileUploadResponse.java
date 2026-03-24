package com.collegeportal.dto;

public class FileUploadResponse {
    private Long id;
    private String fileName;
    private String contentType;
    private String url;
    private String assetType;
    FileUploadResponse(final Long id, final String fileName, final String contentType, final String url, final String assetType) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.url = url;
        this.assetType = assetType;
    }
    public static class FileUploadResponseBuilder {
        private Long id;
        private String fileName;
        private String contentType;
        private String url;
        private String assetType;
        FileUploadResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public FileUploadResponse.FileUploadResponseBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public FileUploadResponse.FileUploadResponseBuilder fileName(final String fileName) {
            this.fileName = fileName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public FileUploadResponse.FileUploadResponseBuilder contentType(final String contentType) {
            this.contentType = contentType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public FileUploadResponse.FileUploadResponseBuilder url(final String url) {
            this.url = url;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public FileUploadResponse.FileUploadResponseBuilder assetType(final String assetType) {
            this.assetType = assetType;
            return this;
        }
        public FileUploadResponse build() {
            return new FileUploadResponse(this.id, this.fileName, this.contentType, this.url, this.assetType);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "FileUploadResponse.FileUploadResponseBuilder(id=" + this.id + ", fileName=" + this.fileName + ", contentType=" + this.contentType + ", url=" + this.url + ", assetType=" + this.assetType + ")";
        }
    }
    public static FileUploadResponse.FileUploadResponseBuilder builder() {
        return new FileUploadResponse.FileUploadResponseBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public String getFileName() {
        return this.fileName;
    }
    public String getContentType() {
        return this.contentType;
    }
    public String getUrl() {
        return this.url;
    }
    public String getAssetType() {
        return this.assetType;
    }
}

