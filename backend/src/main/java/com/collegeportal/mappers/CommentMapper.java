package com.collegeportal.mappers;

import com.collegeportal.dto.CommentDto;
import com.collegeportal.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "eventId", expression = "java(comment.getEvent().getId())")
    @Mapping(target = "parentId", expression = "java(comment.getParent() != null ? comment.getParent().getId() : null)")
    @Mapping(target = "userName", expression = "java(comment.getUser().getFullName())")
    @Mapping(target = "createdAt", expression = "java(comment.getCreatedAt().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME))")
    CommentDto toDto(Comment comment);
}

