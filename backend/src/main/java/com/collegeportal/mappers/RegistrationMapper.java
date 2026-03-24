package com.collegeportal.mappers;

import com.collegeportal.dto.RegistrationDto;
import com.collegeportal.entities.EventRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    @Mapping(target = "eventId", expression = "java(registration.getEvent().getId())")
    @Mapping(target = "eventTitle", expression = "java(registration.getEvent().getTitle())")
    @Mapping(target = "userId", expression = "java(registration.getUser().getId())")
    @Mapping(target = "attendeeName", expression = "java(registration.getUser().getFullName())")
    RegistrationDto toDto(EventRegistration registration);
}

