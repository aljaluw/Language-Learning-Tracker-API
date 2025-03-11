package com.jalu.tracker.service;

import com.jalu.tracker.dto.MediaListDto;
import com.jalu.tracker.dto.MediaLogProgressRequestDto;
import com.jalu.tracker.dto.MediaResponseDto;
import com.jalu.tracker.dto.ProgressLogsDto;
import com.jalu.tracker.entity.MediaLog;
import com.jalu.tracker.entity.MediaProgress;
import com.jalu.tracker.repository.MediaLogRepository;
import com.jalu.tracker.repository.MediaProgressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<MediaResponseDto> getAllMedia() {
        return mediaLogRepository.getAllMedia().stream().map(media -> {
            MediaResponseDto dto = new MediaResponseDto();
            dto.setMediaType(media.getMediaType());
            dto.setTitle(media.getTitle());
            dto.setTotalAmount(media.getTotalAmount());
            dto.setUnit(media.getUnit());
            dto.setStartDate(media.getStartDate());
            dto.setComment(media.getComment());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<MediaListDto> getMediaList() {
        return mediaLogRepository.getAllMedia().stream().map(media -> {
            MediaListDto dto = new MediaListDto();
            dto.setId(media.getId());
            dto.setMediaType(media.getMediaType());
            dto.setTitle(media.getTitle());
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<MediaResponseDto> getMedia(Long id) {
        return mediaLogRepository.findById(id).map(media -> {
            MediaResponseDto dto = new MediaResponseDto();
            dto.setMediaType(media.getMediaType());
            dto.setTitle(media.getTitle());
            dto.setTotalAmount(media.getTotalAmount());
            dto.setUnit(media.getUnit());
            dto.setStartDate(media.getStartDate());
            dto.setComment(media.getComment());
            return dto;
        });
    }

    public List<ProgressLogsDto> getProgressLogs() {
        return mediaProgressRepository.getAllMediaProgressWithMediaLog().stream().map(result -> {
            MediaProgress mediaProgress = (MediaProgress) result[0];
            MediaLog mediaLog = (MediaLog) result[1];

            ProgressLogsDto dto = new ProgressLogsDto();
            dto.setDate(mediaProgress.getDate());
            dto.setMediaType(mediaLog.getMediaType());
            dto.setTitle(mediaLog.getTitle());
            dto.setAmount(mediaProgress.getAmount());
            dto.setUnit(mediaLog.getUnit());
            return dto;
        }).collect(Collectors.toList());
    }
}
