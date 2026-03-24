package com.collegeportal.mappers;

import com.collegeportal.dto.AnnouncementDto;
import com.collegeportal.entities.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    @Mapping(target = "eventId", expression = "java(announcement.getEvent() != null ? announcement.getEvent().getId() : null)")
    @Mapping(target = "authorName", expression = "java(announcement.getAuthor().getFullName())")
    AnnouncementDto toDto(Announcement announcement);
}

