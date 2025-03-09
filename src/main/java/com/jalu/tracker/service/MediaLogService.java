package com.jalu.tracker.service;

import com.jalu.tracker.dto.MediaLogProgressRequestDto;
import com.jalu.tracker.entity.MediaLog;
import com.jalu.tracker.entity.MediaProgress;
import com.jalu.tracker.repository.MediaLogRepository;
import com.jalu.tracker.repository.MediaProgressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MediaLogService {

    private final MediaLogRepository mediaLogRepository;

    private final MediaProgressRepository mediaProgressRepository;


    public MediaLogService(MediaLogRepository mediaLogRepository, MediaProgressRepository mediaProgressRepository) {
        this.mediaLogRepository = mediaLogRepository;
        this.mediaProgressRepository = mediaProgressRepository;
    }

    public MediaLog save(MediaLog mediaLog) {
        return mediaLogRepository.save(mediaLog);
    }

    public List<MediaLog> findAll() {
        return mediaLogRepository.findAll();
    }

    public Optional<MediaLog> findMediaById(Long id) {
        return mediaLogRepository.findById(id);
    }

    public void deleteMedia(Long id) {
        mediaLogRepository.deleteById(id);
    }

    @Transactional
    public String addMediaLog(MediaLogProgressRequestDto request) {

        Integer amount = request.getAmount();;
        MediaLog mediaLog = mediaLogRepository.findByTitle(request.getTitle());

        // If media doesn't exist, create new
        if (mediaLog == null) {
            mediaLog = new MediaLog();
            mediaLog.setMediaType(request.getMediaType());
            mediaLog.setTitle(request.getTitle());
            mediaLog.setTotalAmount(0);
            mediaLog.setUnit(request.getUnit());  // Set unit at creation
            mediaLog.setStartDate(request.getDate() != null ? request.getDate() : LocalDate.now());

            mediaLog = mediaLogRepository.save(mediaLog);
        }

        // Check if unit is consistent before logging progress
        if (mediaLog.getUnit() != request.getUnit()) {
            return "Unit mismatch! Existing unit: " + mediaLog.getUnit();
        }

        Integer currentAmount = mediaLogRepository.findCurrentAmount(request.getTitle());
        int totalAmount = currentAmount + request.getAmount();

        // Log progress
        MediaProgress mediaProgress = new MediaProgress();
        mediaProgress.setMedia(mediaLog);
        mediaProgress.setAmount(request.getAmount());
        mediaLog.setTotalAmount(totalAmount);
        mediaProgress.setDate(request.getDate() != null ? request.getDate() : LocalDate.now());
        mediaProgress.setComment(request.getComment());
        mediaLog.setComment(request.getComment());

        mediaProgressRepository.save(mediaProgress);

        return "Media log and progress added successfully!";
    }
}
