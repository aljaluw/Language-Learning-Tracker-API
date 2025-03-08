package com.jalu.tracker.repository;

import com.jalu.tracker.entity.ReadingProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Long> {
    List<ReadingProgress> findByBookIdAndDateBetween(Long bookId, LocalDate startDate, LocalDate endDate);
}
