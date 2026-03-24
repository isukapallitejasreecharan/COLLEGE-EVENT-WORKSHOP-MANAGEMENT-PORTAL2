package com.collegeportal.services;

import com.collegeportal.dto.EventDto;
import com.collegeportal.dto.EventRequest;
import com.collegeportal.dto.FileUploadResponse;
import com.collegeportal.dto.PageResponse;
import com.collegeportal.dto.VolunteerAssignmentRequest;
import com.collegeportal.entities.Category;
import com.collegeportal.entities.Department;
import com.collegeportal.entities.Event;
import com.collegeportal.entities.EventSession;
import com.collegeportal.entities.EventStatus;
import com.collegeportal.entities.EventVolunteer;
import com.collegeportal.entities.RegistrationStatus;
import com.collegeportal.entities.Speaker;
import com.collegeportal.entities.User;
import com.collegeportal.entities.Volunteer;
import com.collegeportal.exceptions.BadRequestException;
import com.collegeportal.exceptions.ResourceNotFoundException;
import com.collegeportal.mappers.EventMapper;
import com.collegeportal.repositories.CategoryRepository;
import com.collegeportal.repositories.DepartmentRepository;
import com.collegeportal.repositories.EventRegistrationRepository;
import com.collegeportal.repositories.EventRepository;
import com.collegeportal.repositories.EventVolunteerRepository;
import com.collegeportal.repositories.SpeakerRepository;
import com.collegeportal.repositories.VenueRepository;
import com.collegeportal.repositories.VolunteerRepository;
import com.collegeportal.utils.SecurityUtils;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final DepartmentRepository departmentRepository;
    private final VenueRepository venueRepository;
    private final SpeakerRepository speakerRepository;
    private final VolunteerRepository volunteerRepository;
    private final EventVolunteerRepository eventVolunteerRepository;
    private final EventRegistrationRepository eventRegistrationRepository;
    private final EventMapper eventMapper;
    private final FileStorageService fileStorageService;
    private final NotificationService notificationService;
    private final AuditService auditService;
    private final SecurityUtils securityUtils;

    @Cacheable(value = "events", key = "#page + \'-\' + #size + \'-\' + #sortBy + \'-\' + #direction + \'-\' + #search + \'-\' + #categoryId + \'-\' + #departmentId + \'-\' + #status")
    public PageResponse<EventDto> listEvents(String search, Long categoryId, Long departmentId, EventStatus status, int page, int size, String sortBy, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy));
        Specification<Event> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (search != null && !search.isBlank()) {
                String loweredPattern = "%" + search.toLowerCase() + "%";
                String rawPattern = "%" + search + "%";
                predicates.add(cb.or(cb.like(cb.lower(root.get("title")), loweredPattern), cb.like(root.get("description"), rawPattern), cb.like(root.get("tags"), rawPattern)));
            }
            if (categoryId != null) {
                predicates.add(cb.equal(root.get("category").get("id"), categoryId));
            }
            if (departmentId != null) {
                predicates.add(cb.equal(root.get("department").get("id"), departmentId));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
        Page<Event> results = eventRepository.findAll(specification, pageable);
        return PageResponse.<EventDto>builder().content(results.getContent().stream().map(this::toRichDto).toList()).page(results.getNumber()).size(results.getSize()).totalElements(results.getTotalElements()).totalPages(results.getTotalPages()).last(results.isLast()).build();
    }

    public EventDto getEvent(Long id) {
        return toRichDto(fetchEvent(id));
    }

    public List<EventDto> getMyManagedEvents() {
        User currentUser = securityUtils.getCurrentUser();
        return eventRepository.findByOrganizer(currentUser).stream().map(this::toRichDto).toList();
    }

    public List<EventDto> getCalendar(LocalDate from, LocalDate to) {
        return eventRepository.findByStartDateBetween(from, to).stream().map(this::toRichDto).toList();
    }

    @Transactional
    @CacheEvict(value = "events", allEntries = true)
    public EventDto createEvent(EventRequest request) {
        User organizer = securityUtils.getCurrentUser();
        Event event = Event.builder().build();
        applyEventRequest(event, request, organizer);
        Event saved = eventRepository.save(event);
        auditService.log(organizer, "CREATE", "Event", String.valueOf(saved.getId()), "Created event " + saved.getTitle());
        return toRichDto(saved);
    }

    @Transactional
    @CacheEvict(value = "events", allEntries = true)
    public EventDto updateEvent(Long id, EventRequest request) {
        User currentUser = securityUtils.getCurrentUser();
        Event event = fetchEvent(id);
        ensureOwnerOrAdmin(currentUser, event);
        applyEventRequest(event, request, event.getOrganizer());
        Event saved = eventRepository.save(event);
        auditService.log(currentUser, "UPDATE", "Event", String.valueOf(saved.getId()), "Updated event " + saved.getTitle());
        return toRichDto(saved);
    }

    @Transactional
    @CacheEvict(value = "events", allEntries = true)
    public EventDto publishEvent(Long id) {
        User currentUser = securityUtils.getCurrentUser();
        Event event = fetchEvent(id);
        ensureOwnerOrAdmin(currentUser, event);
        event.setStatus(EventStatus.PUBLISHED);
        Event saved = eventRepository.save(event);
        return toRichDto(saved);
    }

    @Transactional
    @CacheEvict(value = "events", allEntries = true)
    public void deleteEvent(Long id) {
        User currentUser = securityUtils.getCurrentUser();
        Event event = fetchEvent(id);
        ensureOwnerOrAdmin(currentUser, event);
        eventRepository.delete(event);
        auditService.log(currentUser, "DELETE", "Event", String.valueOf(id), "Deleted event");
    }

    @Transactional
    public FileUploadResponse uploadPoster(Long id, MultipartFile file) {
        Event event = fetchEvent(id);
        ensureOwnerOrAdmin(securityUtils.getCurrentUser(), event);
        FileUploadResponse response = fileStorageService.store(file, "EVENT_POSTER", securityUtils.getCurrentUser(), event, "PUBLIC");
        event.setPosterUrl(response.getUrl());
        eventRepository.save(event);
        return response;
    }

    @Transactional
    public FileUploadResponse uploadMaterials(Long id, MultipartFile file) {
        Event event = fetchEvent(id);
        ensureOwnerOrAdmin(securityUtils.getCurrentUser(), event);
        FileUploadResponse response = fileStorageService.store(file, "EVENT_MATERIAL", securityUtils.getCurrentUser(), event, "REGISTERED");
        event.setMaterialsUrl(response.getUrl());
        eventRepository.save(event);
        return response;
    }

    @Transactional
    public EventDto assignVolunteer(Long id, VolunteerAssignmentRequest request) {
        Event event = fetchEvent(id);
        Volunteer volunteer = volunteerRepository.findById(request.getVolunteerId()).orElseThrow(() -> new ResourceNotFoundException("Volunteer not found"));
        EventVolunteer assignment = EventVolunteer.builder().event(event).volunteer(volunteer).assignment(request.getAssignment()).checkedIn(false).build();
        eventVolunteerRepository.save(assignment);
        notificationService.create(volunteer.getUser(), "Volunteer assignment", "You were assigned to " + event.getTitle(), com.collegeportal.entities.NotificationType.SYSTEM, "/volunteer");
        return toRichDto(event);
    }

    public List<EventDto> getAssignedVolunteerEvents() {
        User currentUser = securityUtils.getCurrentUser();
        return eventVolunteerRepository.findByVolunteerUserId(currentUser.getId()).stream().map(EventVolunteer::getEvent).distinct().map(this::toRichDto).toList();
    }

    public Event fetchEvent(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    private void applyEventRequest(Event event, EventRequest request, User organizer) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        }
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setPosterUrl(request.getPosterUrl());
        event.setCategory(category);
        event.setDepartment(department);
        event.setOrganizer(organizer);
        event.setVenue(request.getVenueId() == null ? null : venueRepository.findById(request.getVenueId()).orElseThrow(() -> new ResourceNotFoundException("Venue not found")));
        event.setEventType(request.getEventType());
        event.setStartDate(request.getStartDate());
        event.setEndDate(request.getEndDate());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setRegistrationDeadline(request.getRegistrationDeadline());
        event.setMaximumParticipants(request.getMaximumParticipants());
        event.setVisibility(request.getVisibility());
        event.setMultiDay(Boolean.TRUE.equals(request.getMultiDay()));
        event.setRecurring(Boolean.TRUE.equals(request.getRecurring()));
        event.setRecurringRule(request.getRecurringRule());
        event.setResources(request.getResources());
        event.setTags(request.getTags());
        event.setFaq(request.getFaq());
        event.setSponsorsText(request.getSponsorsText());
        event.setRegistrationRequiresApproval(Boolean.TRUE.equals(request.getRegistrationRequiresApproval()));
        event.setRegistrationClosed(Boolean.TRUE.equals(request.getRegistrationClosed()));
        event.setStatus(request.isPublishNow() ? EventStatus.PUBLISHED : EventStatus.DRAFT);
        if (event.getEventCode() == null) {
            event.setEventCode("EVT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        event.getSpeakers().clear();
        if (request.getSpeakerIds() != null && !request.getSpeakerIds().isEmpty()) {
            event.getSpeakers().addAll(speakerRepository.findAllById(request.getSpeakerIds()));
        }
        event.getSessions().clear();
        if (request.getSessions() != null) {
            request.getSessions().forEach(sessionRequest -> {
                Speaker speaker = sessionRequest.getSpeakerId() == null ? null : speakerRepository.findById(sessionRequest.getSpeakerId()).orElseThrow(() -> new ResourceNotFoundException("Speaker not found"));
                event.getSessions().add(EventSession.builder().event(event).title(sessionRequest.getTitle()).description(sessionRequest.getDescription()).sessionDate(sessionRequest.getSessionDate()).startTime(sessionRequest.getStartTime()).endTime(sessionRequest.getEndTime()).speaker(speaker).build());
            });
        }
        if (event.getEndDate().isBefore(event.getStartDate())) {
            throw new BadRequestException("End date cannot be before start date");
        }
    }

    private EventDto toRichDto(Event event) {
        EventDto dto = eventMapper.toDto(event);
        long registered = eventRegistrationRepository.countByEventIdAndStatuses(event.getId(), List.of(RegistrationStatus.APPROVED, RegistrationStatus.PENDING, RegistrationStatus.WAITLISTED));
        return EventDto.builder().id(dto.getId()).title(dto.getTitle()).description(dto.getDescription()).posterUrl(dto.getPosterUrl()).categoryName(dto.getCategoryName()).departmentName(dto.getDepartmentName()).organizerName(dto.getOrganizerName()).venueName(dto.getVenueName()).venueLocation(dto.getVenueLocation()).eventType(dto.getEventType()).startDate(dto.getStartDate()).endDate(dto.getEndDate()).startTime(dto.getStartTime()).endTime(dto.getEndTime()).registrationDeadline(dto.getRegistrationDeadline()).maximumParticipants(dto.getMaximumParticipants()).registeredParticipants(registered).availableSeats(Math.max(0, dto.getMaximumParticipants() - registered)).visibility(dto.getVisibility()).status(dto.getStatus()).multiDay(dto.getMultiDay()).recurring(dto.getRecurring()).recurringRule(dto.getRecurringRule()).resources(dto.getResources()).tags(dto.getTags()).faq(dto.getFaq()).sponsorsText(dto.getSponsorsText()).materialsUrl(dto.getMaterialsUrl()).recordingUrl(dto.getRecordingUrl()).eventCode(dto.getEventCode()).registrationRequiresApproval(dto.getRegistrationRequiresApproval()).registrationClosed(dto.getRegistrationClosed()).sessions(dto.getSessions()).speakers(dto.getSpeakers()).build();
    }

    private void ensureOwnerOrAdmin(User currentUser, Event event) {
        boolean isAdmin = currentUser.getRoles().stream().anyMatch(role -> role.getName().name().equals("ROLE_ADMIN"));
        if (!isAdmin && !event.getOrganizer().getId().equals(currentUser.getId())) {
            throw new BadRequestException("You are not allowed to manage this event");
        }
    }
    public EventService(final EventRepository eventRepository, final CategoryRepository categoryRepository, final DepartmentRepository departmentRepository, final VenueRepository venueRepository, final SpeakerRepository speakerRepository, final VolunteerRepository volunteerRepository, final EventVolunteerRepository eventVolunteerRepository, final EventRegistrationRepository eventRegistrationRepository, final EventMapper eventMapper, final FileStorageService fileStorageService, final NotificationService notificationService, final AuditService auditService, final SecurityUtils securityUtils) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.departmentRepository = departmentRepository;
        this.venueRepository = venueRepository;
        this.speakerRepository = speakerRepository;
        this.volunteerRepository = volunteerRepository;
        this.eventVolunteerRepository = eventVolunteerRepository;
        this.eventRegistrationRepository = eventRegistrationRepository;
        this.eventMapper = eventMapper;
        this.fileStorageService = fileStorageService;
        this.notificationService = notificationService;
        this.auditService = auditService;
        this.securityUtils = securityUtils;
    }
}

