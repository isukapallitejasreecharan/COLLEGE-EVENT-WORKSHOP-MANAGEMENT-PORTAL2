package com.collegeportal.mappers;

import com.collegeportal.dto.EventDto;
import com.collegeportal.dto.EventSessionDto;
import com.collegeportal.entities.Event;
import com.collegeportal.entities.EventSession;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "categoryName", expression = "java(event.getCategory() != null ? event.getCategory().getName() : null)")
    @Mapping(target = "departmentName", expression = "java(event.getDepartment() != null ? event.getDepartment().getName() : null)")
    @Mapping(target = "organizerName", expression = "java(event.getOrganizer() != null ? event.getOrganizer().getFullName() : null)")
    @Mapping(target = "venueName", expression = "java(event.getVenue() != null ? event.getVenue().getName() : null)")
    @Mapping(target = "venueLocation", expression = "java(event.getVenue() != null ? event.getVenue().getLocation() : null)")
    @Mapping(target = "registeredParticipants", ignore = true)
    @Mapping(target = "availableSeats", ignore = true)
    @Mapping(target = "sessions", expression = "java(mapSessions(event.getSessions()))")
    @Mapping(target = "speakers", expression = "java(event.getSpeakers().stream().map(com.collegeportal.entities.Speaker::getName).toList())")
    EventDto toDto(Event event);

    @Mapping(target = "speakerId", expression = "java(session.getSpeaker() != null ? session.getSpeaker().getId() : null)")
    @Mapping(target = "speakerName", expression = "java(session.getSpeaker() != null ? session.getSpeaker().getName() : null)")
    EventSessionDto toSessionDto(EventSession session);

    default List<EventSessionDto> mapSessions(List<EventSession> sessions) {
        return sessions == null ? List.of() : sessions.stream().map(this::toSessionDto).collect(Collectors.toList());
    }
}

