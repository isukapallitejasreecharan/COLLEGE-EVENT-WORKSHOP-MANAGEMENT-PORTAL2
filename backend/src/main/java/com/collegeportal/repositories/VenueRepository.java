package com.collegeportal.repositories;

import com.collegeportal.entities.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}

