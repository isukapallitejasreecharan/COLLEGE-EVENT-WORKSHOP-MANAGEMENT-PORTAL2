package com.collegeportal.services;

import com.collegeportal.dto.FileUploadResponse;
import com.collegeportal.entities.Event;
import com.collegeportal.entities.FileAsset;
import com.collegeportal.entities.User;
import com.collegeportal.repositories.FileAssetRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    @Value("${app.uploads-dir}")
    private String uploadsDir;
    private final FileAssetRepository fileAssetRepository;

    public FileUploadResponse store(MultipartFile file, String assetType, User owner, Event event, String visibility) {
        try {
            String original = StringUtils.cleanPath(file.getOriginalFilename());
            String extension = original.contains(".") ? original.substring(original.lastIndexOf('.')) : "";
            String fileName = UUID.randomUUID() + extension;
            Path root = Paths.get(uploadsDir).toAbsolutePath().normalize();
            Files.createDirectories(root);
            Path target = root.resolve(fileName);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            FileAsset asset = fileAssetRepository.save(FileAsset.builder().owner(owner).event(event).fileName(original).contentType(file.getContentType()).storagePath(target.toString()).visibility(visibility).assetType(assetType).build());
            return FileUploadResponse.builder().id(asset.getId()).fileName(asset.getFileName()).contentType(asset.getContentType()).assetType(assetType).url("/api/files/public/" + fileName).build();
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to store file", ex);
        }
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = Paths.get(uploadsDir).toAbsolutePath().normalize().resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists()) {
                throw new IllegalStateException("File not found");
            }
            return resource;
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to read file", ex);
        }
    }
    public FileStorageService(final FileAssetRepository fileAssetRepository) {
        this.fileAssetRepository = fileAssetRepository;
    }
}

