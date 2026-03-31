package com.collegeportal.services;

import com.collegeportal.dto.AnnouncementDto;
import com.collegeportal.dto.AnnouncementRequest;
import com.collegeportal.entities.Announcement;
import com.collegeportal.entities.Event;
import com.collegeportal.entities.NotificationType;
import com.collegeportal.entities.User;
import com.collegeportal.exceptions.ResourceNotFoundException;
import com.collegeportal.mappers.AnnouncementMapper;
import com.collegeportal.repositories.AnnouncementRepository;
import com.collegeportal.utils.SecurityUtils;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final EventService eventService;
    private final SecurityUtils securityUtils;
    private final NotificationService notificationService;

    @Transactional(readOnly = true)
    public List<AnnouncementDto> listActive() {
        return announcementRepository.findByActiveTrueOrderByPublishedAtDesc().stream().map(announcementMapper::toDto).toList();
    }

    @Transactional
    public AnnouncementDto create(AnnouncementRequest request) {
        User author = securityUtils.getCurrentUser();
        Event event = request.getEventId() == null ? null : eventService.fetchEvent(request.getEventId());
        Announcement announcement = announcementRepository.save(Announcement.builder().author(author).event(event).title(request.getTitle()).content(request.getContent()).active(Boolean.TRUE.equals(request.getActive())).publishedAt(LocalDateTime.now()).build());
        notificationService.create(null, announcement.getTitle(), announcement.getContent(), NotificationType.ANNOUNCEMENT, "/announcements");
        return announcementMapper.toDto(announcement);
    }

    @Transactional
    public AnnouncementDto update(Long id, AnnouncementRequest request) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Announcement not found"));
        announcement.setTitle(request.getTitle());
        announcement.setContent(request.getContent());
        announcement.setActive(Boolean.TRUE.equals(request.getActive()));
        return announcementMapper.toDto(announcementRepository.save(announcement));
    }

    @Transactional
    public void delete(Long id) {
        announcementRepository.deleteById(id);
    }
    public AnnouncementService(final AnnouncementRepository announcementRepository, final AnnouncementMapper announcementMapper, final EventService eventService, final SecurityUtils securityUtils, final NotificationService notificationService) {
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
        this.eventService = eventService;
        this.securityUtils = securityUtils;
        this.notificationService = notificationService;
    }
}

