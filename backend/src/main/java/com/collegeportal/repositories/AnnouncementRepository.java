package com.collegeportal.repositories;

import com.collegeportal.entities.Announcement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByActiveTrueOrderByPublishedAtDesc();
}

