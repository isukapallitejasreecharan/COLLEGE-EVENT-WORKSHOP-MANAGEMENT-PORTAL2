package com.collegeportal.service.impl;

import com.collegeportal.dto.EventDtos;
import com.collegeportal.entity.*;
import com.collegeportal.exception.BadRequestException;
import com.collegeportal.exception.ResourceNotFoundException;
import com.collegeportal.repository.EventRepository;
import com.collegeportal.repository.RegistrationRepository;
import com.collegeportal.repository.UserRepository;
import com.collegeportal.repository.VenueRepository;
import com.collegeportal.service.EventService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public EventServiceImpl(
            EventRepository eventRepository,
            VenueRepository venueRepository,
            UserRepository userRepository,
            RegistrationRepository registrationRepository,
            SimpMessagingTemplate messagingTemplate) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public EventDtos.EventResponse create(EventDtos.EventCreateRequest request, String organizerEmail) {
        User organizer = userRepository.findByEmail(organizerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer not found"));
        Venue venue = venueRepository.findById(request.venueId())
                .orElseThrow(() -> new ResourceNotFoundException("Venue not found"));

        Event event = new Event();
        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setCategory(request.category());
        event.setTags(request.tags());
        event.setEventType(request.eventType());
        event.setDate(request.date());
        event.setStartTime(request.startTime());
        event.setEndTime(request.endTime());
        event.setCapacity(request.capacity());
        event.setRegistrationDeadline(request.registrationDeadline());
        event.setEventPoster(request.eventPoster());
        event.setOrganizer(organizer);
        event.setVenue(venue);
        event.setStatus(EventStatus.PUBLISHED);
        Event saved = eventRepository.save(event);
        return toResponse(saved);
    }

    @Override
    @Cacheable(value = "publishedEvents", key = "#category + '-' + #pageable.pageNumber")
    public Page<EventDtos.EventResponse> listPublished(EventCategory category, Pageable pageable) {
        if (category == null) {
            return eventRepository.findByStatus(EventStatus.PUBLISHED, pageable).map(this::toResponse);
        }
        return eventRepository.findByStatusAndCategory(EventStatus.PUBLISHED, category, pageable).map(this::toResponse);
    }

    @Override
    public void register(Long eventId, String studentEmail) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        User student = userRepository.findByEmail(studentEmail).orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        if (registrationRepository.existsByEventAndStudent(event, student)) {
            throw new BadRequestException("Duplicate registration is not allowed");
        }

        long confirmed = registrationRepository.countByEventAndStatus(event, "CONFIRMED");
        String status = confirmed >= event.getCapacity() ? "WAITLIST" : "CONFIRMED";

        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setStudent(student);
        registration.setQrToken(UUID.randomUUID().toString());
        registration.setStatus(status);
        registrationRepository.save(registration);
        messagingTemplate.convertAndSend("/topic/notifications", "New registration for event: " + event.getTitle());
    }

    private EventDtos.EventResponse toResponse(Event event) {
        return new EventDtos.EventResponse(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getCategory(),
                event.getEventType(),
                event.getDate(),
                event.getStartTime(),
                event.getEndTime(),
                event.getCapacity(),
                event.getStatus(),
                event.getVenue() != null ? event.getVenue().getName() : null,
                event.getOrganizer().getFullName()
        );
    }
}
