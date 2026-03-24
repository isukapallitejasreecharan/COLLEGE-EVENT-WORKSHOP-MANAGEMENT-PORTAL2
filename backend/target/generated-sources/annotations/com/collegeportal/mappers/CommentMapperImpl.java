package com.collegeportal.mappers;

import com.collegeportal.dto.CommentDto;
import com.collegeportal.entities.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T11:17:04+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDto toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setId( comment.getId() );
        commentDto.setContent( comment.getContent() );
        commentDto.setUpvotes( comment.getUpvotes() );
        commentDto.setModerated( comment.getModerated() );

        commentDto.setEventId( comment.getEvent().getId() );
        commentDto.setParentId( comment.getParent() != null ? comment.getParent().getId() : null );
        commentDto.setUserName( comment.getUser().getFullName() );
        commentDto.setCreatedAt( comment.getCreatedAt().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME) );

        return commentDto;
    }
}
