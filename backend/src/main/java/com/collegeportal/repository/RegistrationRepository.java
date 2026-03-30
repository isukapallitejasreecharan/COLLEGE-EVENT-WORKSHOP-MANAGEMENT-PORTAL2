package com.collegeportal.repository;

import com.collegeportal.entity.Event;
import com.collegeportal.entity.Registration;
import com.collegeportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByEventAndStudent(Event event, User student);
    long countByEventAndStatus(Event event, String status);
}
