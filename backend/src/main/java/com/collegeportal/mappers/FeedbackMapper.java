package com.collegeportal.mappers;

import com.collegeportal.dto.FeedbackDto;
import com.collegeportal.entities.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    @Mapping(target = "eventId", expression = "java(feedback.getEvent().getId())")
    @Mapping(target = "eventTitle", expression = "java(feedback.getEvent().getTitle())")
    @Mapping(target = "userName", expression = "java(feedback.getUser().getFullName())")
    FeedbackDto toDto(Feedback feedback);
}

