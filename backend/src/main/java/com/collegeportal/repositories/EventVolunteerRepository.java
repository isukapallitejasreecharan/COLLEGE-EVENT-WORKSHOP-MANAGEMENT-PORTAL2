package com.collegeportal.repositories;

import com.collegeportal.entities.EventVolunteer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventVolunteerRepository extends JpaRepository<EventVolunteer, Long> {
    List<EventVolunteer> findByVolunteerUserId(Long userId);
    List<EventVolunteer> findByEventId(Long eventId);
}

