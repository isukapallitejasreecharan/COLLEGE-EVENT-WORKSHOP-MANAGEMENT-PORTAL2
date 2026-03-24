package com.collegeportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "file_assets")
public class FileAsset extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    @Column(nullable = false, length = 180)
    private String fileName;
    @Column(nullable = false, length = 120)
    private String contentType;
    @Column(nullable = false, length = 400)
    private String storagePath;
    @Column(nullable = false, length = 40)
    private String visibility;
    @Column(nullable = false, length = 50)
    private String assetType;
    public static class FileAssetBuilder {
        private User owner;
        private Event event;
        private String fileName;
        private String contentType;
        private String storagePath;
        private String visibility;
        private String assetType;
        FileAssetBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public FileAsset.FileAssetBuilder owner(final User owner) {
            this.owner = owner;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public FileAsset.FileAssetBuilder event(final Event event) {
            this.event = event;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public FileAsset.FileAssetBuilder fileName(final String fileName) {
            this.fileName = fileName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public FileAsset.FileAssetBuilder contentType(final String contentType) {
            this.contentType = contentType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public FileAsset.FileAssetBuilder storagePath(final String storagePath) {
            this.storagePath = storagePath;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public FileAsset.FileAssetBuilder visibility(final String visibility) {
            this.visibility = visibility;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public FileAsset.FileAssetBuilder assetType(final String assetType) {
            this.assetType = assetType;
            return this;
        }
        public FileAsset build() {
            return new FileAsset(this.owner, this.event, this.fileName, this.contentType, this.storagePath, this.visibility, this.assetType);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "FileAsset.FileAssetBuilder(owner=" + this.owner + ", event=" + this.event + ", fileName=" + this.fileName + ", contentType=" + this.contentType + ", storagePath=" + this.storagePath + ", visibility=" + this.visibility + ", assetType=" + this.assetType + ")";
        }
    }
    public static FileAsset.FileAssetBuilder builder() {
        return new FileAsset.FileAssetBuilder();
    }
    public User getOwner() {
        return this.owner;
    }
    public Event getEvent() {
        return this.event;
    }
    public String getFileName() {
        return this.fileName;
    }
    public String getContentType() {
        return this.contentType;
    }
    public String getStoragePath() {
        return this.storagePath;
    }
    public String getVisibility() {
        return this.visibility;
    }
    public String getAssetType() {
        return this.assetType;
    }
    public void setOwner(final User owner) {
        this.owner = owner;
    }
    public void setEvent(final Event event) {
        this.event = event;
    }
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }
    public void setStoragePath(final String storagePath) {
        this.storagePath = storagePath;
    }
    public void setVisibility(final String visibility) {
        this.visibility = visibility;
    }
    public void setAssetType(final String assetType) {
        this.assetType = assetType;
    }
    public FileAsset() {
    }
    public FileAsset(final User owner, final Event event, final String fileName, final String contentType, final String storagePath, final String visibility, final String assetType) {
        this.owner = owner;
        this.event = event;
        this.fileName = fileName;
        this.contentType = contentType;
        this.storagePath = storagePath;
        this.visibility = visibility;
        this.assetType = assetType;
    }
}

