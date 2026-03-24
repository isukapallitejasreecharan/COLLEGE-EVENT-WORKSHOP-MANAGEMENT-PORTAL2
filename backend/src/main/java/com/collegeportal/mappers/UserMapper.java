package com.collegeportal.mappers;

import com.collegeportal.dto.UserDto;
import com.collegeportal.entities.Role;
import com.collegeportal.entities.User;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "fullName", expression = "java(user.getFullName())")
    @Mapping(target = "departmentName", expression = "java(user.getDepartment() != null ? user.getDepartment().getName() : null)")
    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    UserDto toDto(User user);

    default Set<String> mapRoles(Set<Role> roles) {
        return roles.stream().map(role -> role.getName().name()).collect(Collectors.toSet());
    }
}

