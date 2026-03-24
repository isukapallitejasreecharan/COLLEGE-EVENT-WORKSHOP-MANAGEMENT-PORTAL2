package com.collegeportal.mappers;

import com.collegeportal.dto.AttendanceDto;
import com.collegeportal.entities.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    @Mapping(target = "eventId", expression = "java(attendance.getEvent().getId())")
    @Mapping(target = "eventTitle", expression = "java(attendance.getEvent().getTitle())")
    @Mapping(target = "userId", expression = "java(attendance.getAttendee().getId())")
    @Mapping(target = "attendeeName", expression = "java(attendance.getAttendee().getFullName())")
    AttendanceDto toDto(Attendance attendance);
}

