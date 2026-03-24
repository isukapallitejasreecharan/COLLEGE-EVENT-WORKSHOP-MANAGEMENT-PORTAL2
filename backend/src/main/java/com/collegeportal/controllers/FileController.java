package com.collegeportal.controllers;

import com.collegeportal.services.FileStorageService;
import java.nio.file.Files;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileStorageService fileStorageService;

    @GetMapping("/public/{filename:.+}")
    public ResponseEntity<Resource> servePublic(@PathVariable String filename) throws Exception {
        Resource resource = fileStorageService.loadAsResource(filename);
        String type = Files.probeContentType(resource.getFile().toPath());
        return ResponseEntity.ok().contentType(type != null ? MediaType.parseMediaType(type) : MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }
    public FileController(final FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }
}

