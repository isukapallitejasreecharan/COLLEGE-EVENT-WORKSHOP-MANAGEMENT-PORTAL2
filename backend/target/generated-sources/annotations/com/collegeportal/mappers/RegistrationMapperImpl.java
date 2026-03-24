package com.collegeportal.mappers;

import com.collegeportal.dto.RegistrationDto;
import com.collegeportal.entities.EventRegistration;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T11:17:04+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class RegistrationMapperImpl implements RegistrationMapper {

    @Override
    public RegistrationDto toDto(EventRegistration registration) {
        if ( registration == null ) {
            return null;
        }

        RegistrationDto.RegistrationDtoBuilder registrationDto = RegistrationDto.builder();

        registrationDto.id( registration.getId() );
        registrationDto.status( registration.getStatus() );
        registrationDto.bookmarked( registration.getBookmarked() );
        registrationDto.checkedIn( registration.getCheckedIn() );
        registrationDto.registrationDate( registration.getRegistrationDate() );
        registrationDto.remarks( registration.getRemarks() );

        registrationDto.eventId( registration.getEvent().getId() );
        registrationDto.eventTitle( registration.getEvent().getTitle() );
        registrationDto.userId( registration.getUser().getId() );
        registrationDto.attendeeName( registration.getUser().getFullName() );

        return registrationDto.build();
    }
}
