package com.collegeportal.repositories;

import com.collegeportal.entities.Volunteer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    Optional<Volunteer> findByUserId(Long userId);
}

