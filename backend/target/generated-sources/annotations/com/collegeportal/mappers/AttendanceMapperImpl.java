package com.collegeportal.mappers;

import com.collegeportal.dto.AttendanceDto;
import com.collegeportal.entities.Attendance;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T11:17:04+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class AttendanceMapperImpl implements AttendanceMapper {

    @Override
    public AttendanceDto toDto(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }

        AttendanceDto.AttendanceDtoBuilder attendanceDto = AttendanceDto.builder();

        attendanceDto.id( attendance.getId() );
        attendanceDto.method( attendance.getMethod() );
        attendanceDto.status( attendance.getStatus() );
        attendanceDto.checkInTime( attendance.getCheckInTime() );
        attendanceDto.late( attendance.getLate() );

        attendanceDto.eventId( attendance.getEvent().getId() );
        attendanceDto.eventTitle( attendance.getEvent().getTitle() );
        attendanceDto.userId( attendance.getAttendee().getId() );
        attendanceDto.attendeeName( attendance.getAttendee().getFullName() );

        return attendanceDto.build();
    }
}
