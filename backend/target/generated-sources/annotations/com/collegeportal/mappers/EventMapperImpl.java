package com.collegeportal.mappers;

import com.collegeportal.dto.EventDto;
import com.collegeportal.dto.EventSessionDto;
import com.collegeportal.entities.Event;
import com.collegeportal.entities.EventSession;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T11:17:04+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventDto toDto(Event event) {
        if ( event == null ) {
            return null;
        }

        EventDto.EventDtoBuilder eventDto = EventDto.builder();

        eventDto.id( event.getId() );
        eventDto.title( event.getTitle() );
        eventDto.description( event.getDescription() );
        eventDto.posterUrl( event.getPosterUrl() );
        eventDto.eventType( event.getEventType() );
        eventDto.startDate( event.getStartDate() );
        eventDto.endDate( event.getEndDate() );
        eventDto.startTime( event.getStartTime() );
        eventDto.endTime( event.getEndTime() );
        eventDto.registrationDeadline( event.getRegistrationDeadline() );
        eventDto.maximumParticipants( event.getMaximumParticipants() );
        eventDto.visibility( event.getVisibility() );
        eventDto.status( event.getStatus() );
        eventDto.multiDay( event.getMultiDay() );
        eventDto.recurring( event.getRecurring() );
        eventDto.recurringRule( event.getRecurringRule() );
        eventDto.resources( event.getResources() );
        eventDto.tags( event.getTags() );
        eventDto.faq( event.getFaq() );
        eventDto.sponsorsText( event.getSponsorsText() );
        eventDto.materialsUrl( event.getMaterialsUrl() );
        eventDto.recordingUrl( event.getRecordingUrl() );
        eventDto.eventCode( event.getEventCode() );
        eventDto.registrationRequiresApproval( event.getRegistrationRequiresApproval() );
        eventDto.registrationClosed( event.getRegistrationClosed() );

        eventDto.categoryName( event.getCategory() != null ? event.getCategory().getName() : null );
        eventDto.departmentName( event.getDepartment() != null ? event.getDepartment().getName() : null );
        eventDto.organizerName( event.getOrganizer() != null ? event.getOrganizer().getFullName() : null );
        eventDto.venueName( event.getVenue() != null ? event.getVenue().getName() : null );
        eventDto.venueLocation( event.getVenue() != null ? event.getVenue().getLocation() : null );
        eventDto.sessions( mapSessions(event.getSessions()) );
        eventDto.speakers( event.getSpeakers().stream().map(com.collegeportal.entities.Speaker::getName).toList() );

        return eventDto.build();
    }

    @Override
    public EventSessionDto toSessionDto(EventSession session) {
        if ( session == null ) {
            return null;
        }

        EventSessionDto.EventSessionDtoBuilder eventSessionDto = EventSessionDto.builder();

        eventSessionDto.id( session.getId() );
        eventSessionDto.title( session.getTitle() );
        eventSessionDto.description( session.getDescription() );
        eventSessionDto.sessionDate( session.getSessionDate() );
        eventSessionDto.startTime( session.getStartTime() );
        eventSessionDto.endTime( session.getEndTime() );

        eventSessionDto.speakerId( session.getSpeaker() != null ? session.getSpeaker().getId() : null );
        eventSessionDto.speakerName( session.getSpeaker() != null ? session.getSpeaker().getName() : null );

        return eventSessionDto.build();
    }
}
