package com.collegeportal.repositories;

import com.collegeportal.entities.EventRegistration;
import com.collegeportal.entities.RegistrationStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    Optional<EventRegistration> findByEventIdAndUserId(Long eventId, Long userId);
    List<EventRegistration> findByUserIdOrderByRegistrationDateDesc(Long userId);
    List<EventRegistration> findByEventIdOrderByRegistrationDateDesc(Long eventId);
    long countByEventIdAndStatus(Long eventId, RegistrationStatus status);

    @Query("select count(r) from EventRegistration r where r.event.id = :eventId and r.status in :statuses")
    long countByEventIdAndStatuses(@Param("eventId") Long eventId, @Param("statuses") List<RegistrationStatus> statuses);
}

