package com.collegeportal.repositories;

import com.collegeportal.entities.Certificate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByUserIdOrderByIssuedAtDesc(Long userId);
    Optional<Certificate> findByVerificationCode(String verificationCode);
    Optional<Certificate> findByEventIdAndUserId(Long eventId, Long userId);
}

