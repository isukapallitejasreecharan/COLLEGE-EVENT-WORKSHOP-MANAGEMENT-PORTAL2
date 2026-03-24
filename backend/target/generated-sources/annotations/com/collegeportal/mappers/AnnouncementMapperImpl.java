package com.collegeportal.mappers;

import com.collegeportal.dto.AnnouncementDto;
import com.collegeportal.entities.Announcement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T11:17:04+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class AnnouncementMapperImpl implements AnnouncementMapper {

    @Override
    public AnnouncementDto toDto(Announcement announcement) {
        if ( announcement == null ) {
            return null;
        }

        AnnouncementDto announcementDto = new AnnouncementDto();

        announcementDto.setId( announcement.getId() );
        announcementDto.setTitle( announcement.getTitle() );
        announcementDto.setContent( announcement.getContent() );
        announcementDto.setActive( announcement.getActive() );
        announcementDto.setPublishedAt( announcement.getPublishedAt() );

        announcementDto.setEventId( announcement.getEvent() != null ? announcement.getEvent().getId() : null );
        announcementDto.setAuthorName( announcement.getAuthor().getFullName() );

        return announcementDto;
    }
}
