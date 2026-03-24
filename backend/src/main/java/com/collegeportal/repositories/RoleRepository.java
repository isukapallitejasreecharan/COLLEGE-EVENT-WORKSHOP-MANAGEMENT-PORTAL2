package com.collegeportal.repositories;

import com.collegeportal.entities.Role;
import com.collegeportal.entities.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}

