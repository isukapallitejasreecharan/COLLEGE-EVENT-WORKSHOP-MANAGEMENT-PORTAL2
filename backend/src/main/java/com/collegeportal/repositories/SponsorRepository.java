package com.collegeportal.repositories;

import com.collegeportal.entities.Sponsor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    List<Sponsor> findByEventId(Long eventId);
}

