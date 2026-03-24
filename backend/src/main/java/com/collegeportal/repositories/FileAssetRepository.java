package com.collegeportal.repositories;

import com.collegeportal.entities.FileAsset;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileAssetRepository extends JpaRepository<FileAsset, Long> {
    List<FileAsset> findByEventId(Long eventId);
}

