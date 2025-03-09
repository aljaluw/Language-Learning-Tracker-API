package com.jalu.tracker.repository;

import com.jalu.tracker.entity.MediaProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MediaProgressRepository extends JpaRepository<MediaProgress, Long> {
    List<MediaProgress> findByMediaIdAndDateBetween(Long bookId, LocalDate startDate, LocalDate endDate);
}
