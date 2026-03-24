package com.collegeportal.mappers;

import com.collegeportal.dto.DepartmentDto;
import com.collegeportal.entities.Department;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T11:17:04+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDto toDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDto.DepartmentDtoBuilder departmentDto = DepartmentDto.builder();

        departmentDto.id( department.getId() );
        departmentDto.code( department.getCode() );
        departmentDto.name( department.getName() );
        departmentDto.description( department.getDescription() );

        return departmentDto.build();
    }
}
