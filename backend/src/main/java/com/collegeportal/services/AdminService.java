package com.collegeportal.services;

import com.collegeportal.dto.AuditLogDto;
import com.collegeportal.dto.CategoryDto;
import com.collegeportal.dto.DashboardStatsDto;
import com.collegeportal.dto.DepartmentDto;
import com.collegeportal.dto.PageResponse;
import com.collegeportal.dto.VenueDto;
import com.collegeportal.entities.Announcement;
import com.collegeportal.entities.Category;
import com.collegeportal.entities.Department;
import com.collegeportal.entities.Role;
import com.collegeportal.entities.User;
import com.collegeportal.entities.Venue;
import com.collegeportal.mappers.CategoryMapper;
import com.collegeportal.mappers.DepartmentMapper;
import com.collegeportal.mappers.VenueMapper;
import com.collegeportal.repositories.AnnouncementRepository;
import com.collegeportal.repositories.AuditLogRepository;
import com.collegeportal.repositories.CategoryRepository;
import com.collegeportal.repositories.CertificateRepository;
import com.collegeportal.repositories.DepartmentRepository;
import com.collegeportal.repositories.EventRegistrationRepository;
import com.collegeportal.repositories.EventRepository;
import com.collegeportal.repositories.UserRepository;
import com.collegeportal.repositories.VenueRepository;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final EventRegistrationRepository registrationRepository;
    private final CertificateRepository certificateRepository;
    private final AnnouncementRepository announcementRepository;
    private final AuditLogRepository auditLogRepository;
    private final CategoryRepository categoryRepository;
    private final DepartmentRepository departmentRepository;
    private final VenueRepository venueRepository;
    private final CategoryMapper categoryMapper;
    private final DepartmentMapper departmentMapper;
    private final VenueMapper venueMapper;
    private final EventService eventService;

    public DashboardStatsDto dashboard(Long userId) {
        Map<String, Long> roleDistribution = new LinkedHashMap<>();
        userRepository.findAll().forEach(user -> user.getRoles().forEach(role -> roleDistribution.merge(role.getName().name(), 1L, Long::sum)));
        Map<String, Long> eventsByCategory = new LinkedHashMap<>();
        eventRepository.findAll().forEach(event -> eventsByCategory.merge(event.getCategory().getName(), 1L, Long::sum));
        Map<String, Long> registrationsByDepartment = new LinkedHashMap<>();
        registrationRepository.findAll().forEach(registration -> {
            String key = registration.getUser().getDepartment() != null ? registration.getUser().getDepartment().getName() : "Unassigned";
            registrationsByDepartment.merge(key, 1L, Long::sum);
        });
        return DashboardStatsDto.builder().totalUsers(userRepository.count()).totalEvents(eventRepository.count()).totalRegistrations(registrationRepository.count()).totalCertificates(certificateRepository.count()).activeAnnouncements(announcementRepository.findByActiveTrueOrderByPublishedAtDesc().size()).unreadNotifications(0).upcomingEvents(eventRepository.findTop5ByStatusOrderByCreatedAtDesc(com.collegeportal.entities.EventStatus.PUBLISHED).stream().map(event -> eventService.getEvent(event.getId())).toList()).registrationsByDepartment(registrationsByDepartment).eventsByCategory(eventsByCategory).roleDistribution(roleDistribution).build();
    }

    @Transactional(readOnly = true)
    public PageResponse<AuditLogDto> auditLogs(int page, int size) {
        var result = auditLogRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size));
        return PageResponse.<AuditLogDto>builder().content(result.getContent().stream().map(log -> AuditLogDto.builder().id(log.getId()).userId(log.getUser() != null ? log.getUser().getId() : null).userName(log.getUser() != null ? log.getUser().getFullName() : "System").action(log.getAction()).entityType(log.getEntityType()).entityId(log.getEntityId()).details(log.getDetails()).createdAt(log.getCreatedAt()).build()).toList()).page(result.getNumber()).size(result.getSize()).totalElements(result.getTotalElements()).totalPages(result.getTotalPages()).last(result.isLast()).build();
    }

    public List<CategoryDto> categories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }

    public List<DepartmentDto> departments() {
        return departmentRepository.findAll().stream().map(departmentMapper::toDto).toList();
    }

    public List<VenueDto> venues() {
        return venueRepository.findAll().stream().map(venueMapper::toDto).toList();
    }

    @Transactional
    public CategoryDto saveCategory(Category category) {
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Transactional
    public DepartmentDto saveDepartment(Department department) {
        return departmentMapper.toDto(departmentRepository.save(department));
    }

    @Transactional
    public VenueDto saveVenue(Venue venue) {
        return venueMapper.toDto(venueRepository.save(venue));
    }

    public String exportUsersCsv() {
        StringBuilder builder = new StringBuilder("Name,Email,Roles\n");
        userRepository.findAll().forEach(user -> builder.append(user.getFullName()).append(',').append(user.getEmail()).append(',').append(user.getRoles().stream().map(Role::getName).toList()).append('\n'));
        return builder.toString();
    }
    public AdminService(final UserRepository userRepository, final EventRepository eventRepository, final EventRegistrationRepository registrationRepository, final CertificateRepository certificateRepository, final AnnouncementRepository announcementRepository, final AuditLogRepository auditLogRepository, final CategoryRepository categoryRepository, final DepartmentRepository departmentRepository, final VenueRepository venueRepository, final CategoryMapper categoryMapper, final DepartmentMapper departmentMapper, final VenueMapper venueMapper, final EventService eventService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
        this.certificateRepository = certificateRepository;
        this.announcementRepository = announcementRepository;
        this.auditLogRepository = auditLogRepository;
        this.categoryRepository = categoryRepository;
        this.departmentRepository = departmentRepository;
        this.venueRepository = venueRepository;
        this.categoryMapper = categoryMapper;
        this.departmentMapper = departmentMapper;
        this.venueMapper = venueMapper;
        this.eventService = eventService;
    }
}

