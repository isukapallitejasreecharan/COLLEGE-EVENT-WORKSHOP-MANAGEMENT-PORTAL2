package com.collegeportal.mappers;

import com.collegeportal.dto.VenueDto;
import com.collegeportal.entities.Venue;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T11:17:04+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class VenueMapperImpl implements VenueMapper {

    @Override
    public VenueDto toDto(Venue venue) {
        if ( venue == null ) {
            return null;
        }

        VenueDto.VenueDtoBuilder venueDto = VenueDto.builder();

        venueDto.id( venue.getId() );
        venueDto.name( venue.getName() );
        venueDto.location( venue.getLocation() );
        venueDto.capacity( venue.getCapacity() );
        venueDto.indoor( venue.getIndoor() );
        venueDto.facilities( venue.getFacilities() );

        return venueDto.build();
    }
}
