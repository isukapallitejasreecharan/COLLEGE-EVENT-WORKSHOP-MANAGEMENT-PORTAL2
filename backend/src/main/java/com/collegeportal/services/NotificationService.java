package com.collegeportal.services;

import com.collegeportal.dto.NotificationDto;
import com.collegeportal.entities.Notification;
import com.collegeportal.entities.NotificationType;
import com.collegeportal.entities.User;
import com.collegeportal.mappers.NotificationMapper;
import com.collegeportal.repositories.NotificationRepository;
import java.util.List;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final SimpMessagingTemplate messagingTemplate;

    public Notification create(User user, String title, String message, NotificationType type, String linkUrl) {
        Notification notification = notificationRepository.save(Notification.builder().user(user).title(title).message(message).type(type).isRead(false).linkUrl(linkUrl).build());
        if (user != null) {
            messagingTemplate.convertAndSendToUser(user.getEmail(), "/queue/notifications", notificationMapper.toDto(notification));
        } else {
            messagingTemplate.convertAndSend("/topic/notifications", notificationMapper.toDto(notification));
        }
        return notification;
    }

    public List<NotificationDto> getMyNotifications(Long userId) {
        return notificationRepository.findTop20ByUserIdOrderByCreatedAtDesc(userId).stream().map(notificationMapper::toDto).toList();
    }

    public long unreadCount(Long userId) {
        return notificationRepository.countByUserIdAndIsReadFalse(userId);
    }

    public void markRead(Notification notification) {
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }

    public Notification findById(Long id) {
        return notificationRepository.findById(id).orElseThrow();
    }
    public NotificationService(final NotificationRepository notificationRepository, final NotificationMapper notificationMapper, final SimpMessagingTemplate messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.messagingTemplate = messagingTemplate;
    }
}

