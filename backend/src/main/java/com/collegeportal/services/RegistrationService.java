package com.collegeportal.services;

import com.collegeportal.dto.RegistrationDto;
import com.collegeportal.entities.Event;
import com.collegeportal.entities.EventRegistration;
import com.collegeportal.entities.EventStatus;
import com.collegeportal.entities.NotificationType;
import com.collegeportal.entities.RegistrationStatus;
import com.collegeportal.entities.User;
import com.collegeportal.exceptions.BadRequestException;
import com.collegeportal.exceptions.ResourceNotFoundException;
import com.collegeportal.mappers.RegistrationMapper;
import com.collegeportal.repositories.EventRegistrationRepository;
import com.collegeportal.utils.SecurityUtils;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final EventService eventService;
    private final EventRegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final NotificationService notificationService;
    private final AuditService auditService;
    private final SecurityUtils securityUtils;

    @Transactional
    public RegistrationDto register(Long eventId) {
        User user = securityUtils.getCurrentUser();
        Event event = eventService.fetchEvent(eventId);
        if (event.getStatus() != EventStatus.PUBLISHED) {
            throw new BadRequestException("Event is not open for registration");
        }
        if (Boolean.TRUE.equals(event.getRegistrationClosed()) || event.getRegistrationDeadline().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Registration is closed for this event");
        }
        registrationRepository.findByEventIdAndUserId(eventId, user.getId()).ifPresent(existing -> {
            throw new BadRequestException("You have already registered for this event");
        });
        long activeRegistrations = registrationRepository.countByEventIdAndStatuses(eventId, List.of(RegistrationStatus.PENDING, RegistrationStatus.APPROVED, RegistrationStatus.WAITLISTED));
        RegistrationStatus status = event.getRegistrationRequiresApproval() ? RegistrationStatus.PENDING : RegistrationStatus.APPROVED;
        if (activeRegistrations >= event.getMaximumParticipants()) {
            status = RegistrationStatus.WAITLISTED;
        }
        EventRegistration registration = registrationRepository.save(EventRegistration.builder().event(event).user(user).status(status).bookmarked(false).checkedIn(false).registrationDate(LocalDateTime.now()).remarks(status == RegistrationStatus.WAITLISTED ? "Added to waitlist" : "Registered successfully").build());
        notificationService.create(user, "Registration updated", "Status: " + status + " for " + event.getTitle(), NotificationType.REGISTRATION, "/registrations");
        notificationService.create(event.getOrganizer(), "New registration", user.getFullName() + " registered for " + event.getTitle(), NotificationType.REGISTRATION, "/organizer/participants");
        auditService.log(user, "REGISTER_EVENT", "Event", String.valueOf(eventId), "Registration status " + status);
        return registrationMapper.toDto(registration);
    }

    @Transactional
    public RegistrationDto cancel(Long eventId) {
        User user = securityUtils.getCurrentUser();
        EventRegistration registration = registrationRepository.findByEventIdAndUserId(eventId, user.getId()).orElseThrow(() -> new ResourceNotFoundException("Registration not found"));
        registration.setStatus(RegistrationStatus.CANCELLED);
        registration.setCancelledAt(LocalDateTime.now());
        return registrationMapper.toDto(registrationRepository.save(registration));
    }

    @Transactional(readOnly = true)
    public List<RegistrationDto> myRegistrations() {
        User user = securityUtils.getCurrentUser();
        return registrationRepository.findByUserIdOrderByRegistrationDateDesc(user.getId()).stream().map(registrationMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<RegistrationDto> participants(Long eventId) {
        return registrationRepository.findByEventIdOrderByRegistrationDateDesc(eventId).stream().map(registrationMapper::toDto).toList();
    }

    @Transactional
    public RegistrationDto updateStatus(Long registrationId, RegistrationStatus status, String remarks) {
        EventRegistration registration = registrationRepository.findById(registrationId).orElseThrow(() -> new ResourceNotFoundException("Registration not found"));
        registration.setStatus(status);
        registration.setRemarks(remarks);
        registration = registrationRepository.save(registration);
        notificationService.create(registration.getUser(), "Registration reviewed", "Your registration for " + registration.getEvent().getTitle() + " is now " + status, NotificationType.REGISTRATION, "/registrations");
        return registrationMapper.toDto(registration);
    }

    @Transactional
    public RegistrationDto toggleBookmark(Long eventId) {
        User user = securityUtils.getCurrentUser();
        EventRegistration registration = registrationRepository.findByEventIdAndUserId(eventId, user.getId()).orElseThrow(() -> new ResourceNotFoundException("Registration not found"));
        registration.setBookmarked(!Boolean.TRUE.equals(registration.getBookmarked()));
        return registrationMapper.toDto(registrationRepository.save(registration));
    }

    @Transactional(readOnly = true)
    public String exportParticipantsCsv(Long eventId) {
        StringBuilder builder = new StringBuilder("Name,Email,Status,Registered At\n");
        registrationRepository.findByEventIdOrderByRegistrationDateDesc(eventId).forEach(registration -> builder.append(registration.getUser().getFullName()).append(',').append(registration.getUser().getEmail()).append(',').append(registration.getStatus()).append(',').append(registration.getRegistrationDate()).append('\n'));
        return builder.toString();
    }
    public RegistrationService(final EventService eventService, final EventRegistrationRepository registrationRepository, final RegistrationMapper registrationMapper, final NotificationService notificationService, final AuditService auditService, final SecurityUtils securityUtils) {
        this.eventService = eventService;
        this.registrationRepository = registrationRepository;
        this.registrationMapper = registrationMapper;
        this.notificationService = notificationService;
        this.auditService = auditService;
        this.securityUtils = securityUtils;
    }
}

