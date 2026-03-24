package com.collegeportal.services;

import com.collegeportal.dto.CertificateDto;
import com.collegeportal.entities.Certificate;
import com.collegeportal.entities.Event;
import com.collegeportal.entities.NotificationType;
import com.collegeportal.entities.User;
import com.collegeportal.exceptions.BadRequestException;
import com.collegeportal.exceptions.ResourceNotFoundException;
import com.collegeportal.mappers.CertificateMapper;
import com.collegeportal.repositories.AttendanceRepository;
import com.collegeportal.repositories.CertificateRepository;
import com.collegeportal.repositories.UserRepository;
import com.collegeportal.utils.CertificatePdfUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final EventService eventService;
    private final CertificateMapper certificateMapper;
    private final CertificatePdfUtil certificatePdfUtil;
    private final NotificationService notificationService;
    private final MailService mailService;
    @Value("${app.uploads-dir}")
    private String uploadsDir;

    @Transactional
    public CertificateDto generate(Long eventId, Long userId) {
        Event event = eventService.fetchEvent(eventId);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        attendanceRepository.findByEventIdAndAttendeeId(eventId, userId).orElseThrow(() -> new BadRequestException("Attendance is required before generating a certificate"));
        Certificate existing = certificateRepository.findByEventIdAndUserId(eventId, userId).orElse(null);
        if (existing != null) {
            return certificateMapper.toDto(existing);
        }
        Certificate certificate = Certificate.builder().event(event).user(user).certificateNumber("CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()).verificationCode(UUID.randomUUID().toString()).pdfUrl("").issuedAt(LocalDateTime.now()).emailed(false).build();
        certificate = certificateRepository.save(certificate);
        byte[] pdfBytes = certificatePdfUtil.generate(certificate);
        String fileName = "certificate-" + certificate.getCertificateNumber() + ".pdf";
        Path dir = Paths.get(uploadsDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(dir);
            Files.write(dir.resolve(fileName), pdfBytes);
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to save certificate PDF", ex);
        }
        certificate.setPdfUrl("/api/files/public/" + fileName);
        certificate.setEmailed(true);
        certificate = certificateRepository.save(certificate);
        notificationService.create(user, "Certificate ready", "Your certificate for " + event.getTitle() + " is now available.", NotificationType.CERTIFICATE, "/certificates");
        mailService.sendMail(user.getEmail(), "Certificate issued", "Your certificate is ready: " + certificate.getPdfUrl());
        return certificateMapper.toDto(certificate);
    }

    public List<CertificateDto> myCertificates(Long userId) {
        return certificateRepository.findByUserIdOrderByIssuedAtDesc(userId).stream().map(certificateMapper::toDto).toList();
    }

    public CertificateDto verify(String code) {
        return certificateRepository.findByVerificationCode(code).map(certificateMapper::toDto).orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
    }

    public Resource download(Long certificateId) {
        Certificate certificate = certificateRepository.findById(certificateId).orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
        try {
            Path path = Paths.get(uploadsDir).toAbsolutePath().normalize().resolve(certificate.getPdfUrl().substring(certificate.getPdfUrl().lastIndexOf('/') + 1));
            return new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to read certificate file", ex);
        }
    }
    public CertificateService(final CertificateRepository certificateRepository, final AttendanceRepository attendanceRepository, final UserRepository userRepository, final EventService eventService, final CertificateMapper certificateMapper, final CertificatePdfUtil certificatePdfUtil, final NotificationService notificationService, final MailService mailService) {
        this.certificateRepository = certificateRepository;
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.eventService = eventService;
        this.certificateMapper = certificateMapper;
        this.certificatePdfUtil = certificatePdfUtil;
        this.notificationService = notificationService;
        this.mailService = mailService;
    }
}

