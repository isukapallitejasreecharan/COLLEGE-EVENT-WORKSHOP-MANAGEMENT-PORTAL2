package com.collegeportal.repositories;

import com.collegeportal.entities.Event;
import com.collegeportal.entities.EventStatus;
import com.collegeportal.entities.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    Optional<Event> findByEventCode(String eventCode);
    List<Event> findTop5ByStatusOrderByCreatedAtDesc(EventStatus status);
    List<Event> findByOrganizer(User organizer);
    List<Event> findByStartDateBetween(LocalDate from, LocalDate to);
}

