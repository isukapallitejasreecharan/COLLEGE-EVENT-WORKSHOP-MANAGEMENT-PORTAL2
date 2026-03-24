package com.collegeportal.services;

import com.collegeportal.dto.CommentDto;
import com.collegeportal.dto.CommentRequest;
import com.collegeportal.entities.Comment;
import com.collegeportal.entities.User;
import com.collegeportal.exceptions.ResourceNotFoundException;
import com.collegeportal.mappers.CommentMapper;
import com.collegeportal.repositories.CommentRepository;
import com.collegeportal.utils.SecurityUtils;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final EventService eventService;
    private final CommentMapper commentMapper;
    private final SecurityUtils securityUtils;

    @Transactional
    public CommentDto add(Long eventId, CommentRequest request) {
        User user = securityUtils.getCurrentUser();
        Comment parent = request.getParentId() == null ? null : commentRepository.findById(request.getParentId()).orElseThrow(() -> new ResourceNotFoundException("Parent comment not found"));
        Comment comment = commentRepository.save(Comment.builder().event(eventService.fetchEvent(eventId)).user(user).parent(parent).content(request.getContent()).upvotes(0).moderated(true).build());
        return commentMapper.toDto(comment);
    }

    public List<CommentDto> eventComments(Long eventId) {
        return commentRepository.findByEventIdAndModeratedTrueOrderByCreatedAtDesc(eventId).stream().map(commentMapper::toDto).toList();
    }

    @Transactional
    public CommentDto upvote(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        comment.setUpvotes(comment.getUpvotes() + 1);
        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Transactional
    public CommentDto moderate(Long commentId, boolean approved) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        comment.setModerated(approved);
        return commentMapper.toDto(commentRepository.save(comment));
    }
    public CommentService(final CommentRepository commentRepository, final EventService eventService, final CommentMapper commentMapper, final SecurityUtils securityUtils) {
        this.commentRepository = commentRepository;
        this.eventService = eventService;
        this.commentMapper = commentMapper;
        this.securityUtils = securityUtils;
    }
}

