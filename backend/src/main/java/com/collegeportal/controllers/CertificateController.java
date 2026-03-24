package com.collegeportal.controllers;

import com.collegeportal.dto.ApiResponse;
import com.collegeportal.dto.CertificateDto;
import com.collegeportal.services.CertificateService;
import com.collegeportal.services.UserService;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificates")
public class CertificateController {
    private final CertificateService certificateService;
    private final UserService userService;

    @PreAuthorize("hasAnyRole(\'ADMIN\',\'ORGANIZER\')")
    @PostMapping("/generate")
    public ApiResponse<CertificateDto> generate(@RequestParam Long eventId, @RequestParam Long userId) {
        return ApiResponse.<CertificateDto>builder().success(true).message("Certificate generated").data(certificateService.generate(eventId, userId)).build();
    }

    @GetMapping("/verify/{code}")
    public ApiResponse<CertificateDto> verify(@PathVariable String code) {
        return ApiResponse.<CertificateDto>builder().success(true).message("Certificate verified").data(certificateService.verify(code)).build();
    }

    @PreAuthorize("hasAnyRole(\'STUDENT\',\'FACULTY\',\'ADMIN\',\'ORGANIZER\')")
    @GetMapping("/my")
    public ApiResponse<List<CertificateDto>> myCertificates() {
        return ApiResponse.<List<CertificateDto>>builder().success(true).message("Certificates fetched").data(certificateService.myCertificates(userService.getCurrentUserEntity().getId())).build();
    }

    @PreAuthorize("hasAnyRole(\'STUDENT\',\'FACULTY\',\'ADMIN\',\'ORGANIZER\')")
    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Resource resource = certificateService.download(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificate.pdf").body(resource);
    }
    public CertificateController(final CertificateService certificateService, final UserService userService) {
        this.certificateService = certificateService;
        this.userService = userService;
    }
}

