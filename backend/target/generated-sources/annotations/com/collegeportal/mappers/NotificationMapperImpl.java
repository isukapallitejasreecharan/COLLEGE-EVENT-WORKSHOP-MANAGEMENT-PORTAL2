package com.collegeportal.mappers;

import com.collegeportal.dto.NotificationDto;
import com.collegeportal.entities.Notification;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T11:17:04+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationDto toDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationDto.NotificationDtoBuilder notificationDto = NotificationDto.builder();

        notificationDto.id( notification.getId() );
        notificationDto.title( notification.getTitle() );
        notificationDto.message( notification.getMessage() );
        notificationDto.type( notification.getType() );
        notificationDto.isRead( notification.getIsRead() );
        notificationDto.linkUrl( notification.getLinkUrl() );
        notificationDto.createdAt( notification.getCreatedAt() );

        return notificationDto.build();
    }
}
