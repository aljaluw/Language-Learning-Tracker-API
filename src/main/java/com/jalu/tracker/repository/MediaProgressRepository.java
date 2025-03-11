package com.jalu.tracker.repository;

import com.jalu.tracker.entity.MediaLog;
import com.jalu.tracker.entity.MediaProgress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MediaProgressRepository extends JpaRepository<MediaProgress, Long> {
    List<MediaProgress> findByMediaIdAndDateBetween(Long bookId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT mp, mp.media FROM MediaProgress mp")
    List<Object[]> getAllMediaProgressWithMediaLog();
}
