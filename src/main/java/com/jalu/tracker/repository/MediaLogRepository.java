package com.jalu.tracker.repository;

import com.jalu.tracker.entity.MediaLog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MediaLogRepository extends JpaRepository<MediaLog, Long> {

    MediaLog findByTitle(@Param("title") String title);

    @Transactional
    @Query(value = "SELECT m.totalAmount FROM MediaLog m WHERE m.title = :title")
    Integer findCurrentAmount(@Param("title") String title);

    @Transactional
    @Query(value = "SELECT m from MediaLog m")
    List<MediaLog> getAllMedia();

}
