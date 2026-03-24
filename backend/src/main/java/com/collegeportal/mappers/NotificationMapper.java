package com.collegeportal.mappers;

import com.collegeportal.dto.NotificationDto;
import com.collegeportal.entities.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDto toDto(Notification notification);
}

