package com.collegeportal.mappers;

import com.collegeportal.dto.CategoryDto;
import com.collegeportal.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
}

