package com.jalu.tracker.service;

import com.jalu.tracker.entity.MediaLog;
import com.jalu.tracker.entity.MediaProgress;
import com.jalu.tracker.repository.MediaLogRepository;
import com.jalu.tracker.repository.MediaProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReadingProgressService {

    @Autowired
    private MediaProgressRepository mediaProgressRepository;

    @Autowired
    private MediaLogRepository mediaLogRepository;

    public String addProgress(Long mediaId, Integer amount, LocalDate date, String comment) {
        Optional<MediaLog> mediaOptional = mediaLogRepository.findById(mediaId);

        if (mediaOptional.isEmpty()) {
            return "Media not found";
        }

        MediaLog mediaLog = mediaOptional.get();
        MediaProgress progress = new MediaProgress();
        progress.setMedia(mediaLog);
        progress.setDate(date);
        progress.setAmount(amount);
        progress.setComment(comment);

        mediaProgressRepository.save(progress);

        return "Progress added successfully";
    }

    public int getTotalProgressAmount(Long bookId, LocalDate startDate, LocalDate endDate) {
        List<MediaProgress> progressList = mediaProgressRepository.findByMediaIdAndDateBetween(bookId, startDate, endDate);

        // Sum up the characters read within the specified date range
        return progressList.stream()
                .mapToInt(MediaProgress::getAmount)
                .sum();
    }
}
