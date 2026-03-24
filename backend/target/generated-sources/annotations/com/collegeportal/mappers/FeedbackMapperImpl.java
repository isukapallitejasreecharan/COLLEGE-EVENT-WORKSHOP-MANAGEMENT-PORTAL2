package com.collegeportal.mappers;

import com.collegeportal.dto.FeedbackDto;
import com.collegeportal.entities.Feedback;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T11:17:04+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class FeedbackMapperImpl implements FeedbackMapper {

    @Override
    public FeedbackDto toDto(Feedback feedback) {
        if ( feedback == null ) {
            return null;
        }

        FeedbackDto feedbackDto = new FeedbackDto();

        feedbackDto.setId( feedback.getId() );
        feedbackDto.setRating( feedback.getRating() );
        feedbackDto.setComment( feedback.getComment() );
        feedbackDto.setApproved( feedback.getApproved() );

        feedbackDto.setEventId( feedback.getEvent().getId() );
        feedbackDto.setEventTitle( feedback.getEvent().getTitle() );
        feedbackDto.setUserName( feedback.getUser().getFullName() );

        return feedbackDto;
    }
}
