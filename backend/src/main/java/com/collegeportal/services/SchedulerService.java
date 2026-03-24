package com.collegeportal.services;

import com.collegeportal.entities.Event;
import com.collegeportal.entities.NotificationType;
import com.collegeportal.repositories.EventRegistrationRepository;
import com.collegeportal.repositories.EventRepository;
import java.time.LocalDate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SchedulerService.class);
    private final EventRepository eventRepository;
    private final EventRegistrationRepository registrationRepository;
    private final NotificationService notificationService;

    @Scheduled(cron = "${app.reminders.cron}")
    public void sendEventReminders() {
        for (Event event : eventRepository.findByStartDateBetween(LocalDate.now(), LocalDate.now().plusDays(1))) {
            registrationRepository.findByEventIdOrderByRegistrationDateDesc(event.getId()).forEach(registration -> notificationService.create(registration.getUser(), "Event reminder", "Reminder: " + event.getTitle() + " starts on " + event.getStartDate(), NotificationType.REMINDER, "/registrations"));
        }
        log.info("Reminder job completed");
    }
    public SchedulerService(final EventRepository eventRepository, final EventRegistrationRepository registrationRepository, final NotificationService notificationService) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
        this.notificationService = notificationService;
    }
}

