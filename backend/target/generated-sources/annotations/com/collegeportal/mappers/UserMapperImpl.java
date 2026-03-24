package com.collegeportal.mappers;

import com.collegeportal.dto.UserDto;
import com.collegeportal.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T11:17:04+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.email( user.getEmail() );
        userDto.phone( user.getPhone() );
        userDto.bio( user.getBio() );
        userDto.profileImageUrl( user.getProfileImageUrl() );
        userDto.studentId( user.getStudentId() );
        userDto.employeeId( user.getEmployeeId() );
        userDto.enabled( user.getEnabled() );
        userDto.emailVerified( user.getEmailVerified() );
        userDto.lastLoginAt( user.getLastLoginAt() );

        userDto.fullName( user.getFullName() );
        userDto.departmentName( user.getDepartment() != null ? user.getDepartment().getName() : null );
        userDto.roles( mapRoles(user.getRoles()) );

        return userDto.build();
    }
}
