package com.collegeportal.services;

import com.collegeportal.dto.AttendanceDto;
import com.collegeportal.dto.AttendanceRequest;
import com.collegeportal.entities.Attendance;
import com.collegeportal.entities.AttendanceStatus;
import com.collegeportal.entities.Event;
import com.collegeportal.entities.EventRegistration;
import com.collegeportal.entities.NotificationType;
import com.collegeportal.entities.User;
import com.collegeportal.exceptions.BadRequestException;
import com.collegeportal.exceptions.ResourceNotFoundException;
import com.collegeportal.mappers.AttendanceMapper;
import com.collegeportal.repositories.AttendanceRepository;
import com.collegeportal.repositories.EventRegistrationRepository;
import com.collegeportal.repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EventRegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final AttendanceMapper attendanceMapper;
    private final EventService eventService;
    private final NotificationService notificationService;

    @Transactional
    public AttendanceDto checkIn(Long eventId, AttendanceRequest request) {
        Event event = eventService.fetchEvent(eventId);
        EventRegistration registration = resolveRegistration(eventId, request);
        attendanceRepository.findByEventIdAndAttendeeId(eventId, registration.getUser().getId()).ifPresent(existing -> {
            throw new BadRequestException("Attendance already recorded");
        });
        LocalDateTime checkInTime = LocalDateTime.now();
        boolean late = checkInTime.toLocalDate().equals(event.getStartDate()) && checkInTime.toLocalTime().isAfter(event.getStartTime().plusMinutes(15));
        AttendanceStatus status = request.getStatus() != null ? request.getStatus() : (late ? AttendanceStatus.LATE : AttendanceStatus.PRESENT);
        Attendance attendance = attendanceRepository.save(Attendance.builder().registration(registration).event(event).attendee(registration.getUser()).method(request.getMethod()).status(status).checkInTime(checkInTime).late(late).build());
        registration.setCheckedIn(true);
        registrationRepository.save(registration);
        notificationService.create(registration.getUser(), "Attendance recorded", "You were checked in for " + event.getTitle(), NotificationType.ATTENDANCE, "/attendance");
        return attendanceMapper.toDto(attendance);
    }

    public List<AttendanceDto> eventAttendance(Long eventId) {
        return attendanceRepository.findByEventId(eventId).stream().map(attendanceMapper::toDto).toList();
    }

    public String generateQrPayload(Long eventId, Long userId) {
        return eventService.fetchEvent(eventId).getEventCode() + ":" + userId;
    }

    private EventRegistration resolveRegistration(Long eventId, AttendanceRequest request) {
        if (request.getRegistrationId() != null) {
            return registrationRepository.findById(request.getRegistrationId()).orElseThrow(() -> new ResourceNotFoundException("Registration not found"));
        }
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return registrationRepository.findByEventIdAndUserId(eventId, user.getId()).orElseThrow(() -> new ResourceNotFoundException("Registration not found"));
        }
        throw new BadRequestException("registrationId or userId is required");
    }
    public AttendanceService(final AttendanceRepository attendanceRepository, final EventRegistrationRepository registrationRepository, final UserRepository userRepository, final AttendanceMapper attendanceMapper, final EventService eventService, final NotificationService notificationService) {
        this.attendanceRepository = attendanceRepository;
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.attendanceMapper = attendanceMapper;
        this.eventService = eventService;
        this.notificationService = notificationService;
    }
}

