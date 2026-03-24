package com.collegeportal.mappers;

import com.collegeportal.dto.DepartmentDto;
import com.collegeportal.entities.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);
}

