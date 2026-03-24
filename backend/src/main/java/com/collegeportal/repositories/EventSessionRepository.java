package com.collegeportal.repositories;

import com.collegeportal.entities.EventSession;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventSessionRepository extends JpaRepository<EventSession, Long> {
    List<EventSession> findByEventIdOrderBySessionDateAscStartTimeAsc(Long eventId);
}

