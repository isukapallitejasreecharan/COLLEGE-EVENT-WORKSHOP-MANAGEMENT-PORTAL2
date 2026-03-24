package com.collegeportal.services;

import com.collegeportal.dto.FeedbackDto;
import com.collegeportal.dto.FeedbackRequest;
import com.collegeportal.entities.Feedback;
import com.collegeportal.entities.User;
import com.collegeportal.exceptions.BadRequestException;
import com.collegeportal.exceptions.ResourceNotFoundException;
import com.collegeportal.mappers.FeedbackMapper;
import com.collegeportal.repositories.FeedbackRepository;
import com.collegeportal.utils.SecurityUtils;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final EventService eventService;
    private final FeedbackMapper feedbackMapper;
    private final SecurityUtils securityUtils;

    @Transactional
    public FeedbackDto submit(Long eventId, FeedbackRequest request) {
        User user = securityUtils.getCurrentUser();
        feedbackRepository.findByEventIdAndUserId(eventId, user.getId()).ifPresent(existing -> {
            throw new BadRequestException("Feedback already submitted for this event");
        });
        Feedback feedback = feedbackRepository.save(Feedback.builder().event(eventService.fetchEvent(eventId)).user(user).rating(request.getRating()).comment(request.getComment()).approved(true).build());
        return feedbackMapper.toDto(feedback);
    }

    public List<FeedbackDto> eventFeedback(Long eventId) {
        return feedbackRepository.findByEventIdAndApprovedTrue(eventId).stream().map(feedbackMapper::toDto).toList();
    }

    @Transactional
    public FeedbackDto moderate(Long feedbackId, boolean approved) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
        feedback.setApproved(approved);
        return feedbackMapper.toDto(feedbackRepository.save(feedback));
    }
    public FeedbackService(final FeedbackRepository feedbackRepository, final EventService eventService, final FeedbackMapper feedbackMapper, final SecurityUtils securityUtils) {
        this.feedbackRepository = feedbackRepository;
        this.eventService = eventService;
        this.feedbackMapper = feedbackMapper;
        this.securityUtils = securityUtils;
    }
}

