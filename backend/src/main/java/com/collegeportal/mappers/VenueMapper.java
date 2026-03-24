package com.collegeportal.mappers;

import com.collegeportal.dto.VenueDto;
import com.collegeportal.entities.Venue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    VenueDto toDto(Venue venue);
}

