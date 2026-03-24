package com.collegeportal.repositories;

import com.collegeportal.entities.Attendance;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByEventIdAndAttendeeId(Long eventId, Long attendeeId);
    List<Attendance> findByEventId(Long eventId);
    long countByEventId(Long eventId);
}

