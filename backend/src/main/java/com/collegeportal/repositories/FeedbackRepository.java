package com.collegeportal.repositories;

import com.collegeportal.entities.Feedback;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByEventIdAndApprovedTrue(Long eventId);
    Optional<Feedback> findByEventIdAndUserId(Long eventId, Long userId);
}

