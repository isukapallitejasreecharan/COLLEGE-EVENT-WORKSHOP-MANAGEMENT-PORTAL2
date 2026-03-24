package com.collegeportal.services;

import com.collegeportal.entities.AuditLog;
import com.collegeportal.entities.User;
import com.collegeportal.repositories.AuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private final AuditLogRepository auditLogRepository;

    public void log(User user, String action, String entityType, String entityId, String details) {
        auditLogRepository.save(AuditLog.builder().user(user).action(action).entityType(entityType).entityId(entityId).details(details).build());
    }
    public AuditService(final AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }
}

