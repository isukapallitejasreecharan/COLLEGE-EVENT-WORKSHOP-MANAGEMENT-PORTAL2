package com.collegeportal.repository;

import com.collegeportal.entity.Event;
import com.collegeportal.entity.EventCategory;
import com.collegeportal.entity.EventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByStatusAndCategory(EventStatus status, EventCategory category, Pageable pageable);
    Page<Event> findByStatus(EventStatus status, Pageable pageable);
}
