package com.jalu.tracker.controller;

import com.jalu.tracker.dto.MediaListDto;
import com.jalu.tracker.dto.MediaLogProgressRequestDto;
import com.jalu.tracker.dto.MediaResponseDto;
import com.jalu.tracker.dto.ProgressLogsDto;
import com.jalu.tracker.entity.MediaLog;
import com.jalu.tracker.service.MediaLogService;
import com.jalu.tracker.service.ReadingProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaLogController {

    @Autowired
    private MediaLogService mediaLogService;

    @Autowired
    private ReadingProgressService readingProgressService;

//    @PostMapping("log")
//    public MediaLog addMediaLog(@RequestBody MediaLog mediaLog) {
//        return mediaLogService.save(mediaLog);
//    }

//    @GetMapping
//    public List<MediaLog> getAllMedia() {
//        return mediaLogService.findAll();
//    }

    @PostMapping("/log")
    public ResponseEntity<String> logMediaProgress(@RequestBody MediaLogProgressRequestDto request) {
        String response = mediaLogService.addMediaLog(request);

        if (response.startsWith("Unit mismatch!")) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public List<MediaResponseDto> getAllMedia() {
        return mediaLogService.getAllMedia();
    }

    @GetMapping("/list")
    public List<MediaListDto> getMediaList() {
        return mediaLogService.getMediaList();
    }

    @GetMapping("/{mediaId}")
    public Optional<MediaResponseDto> getMedia(@PathVariable Long mediaId) {
        return mediaLogService.getMedia(mediaId);
    }

    @GetMapping("/all-logs")
    public List<ProgressLogsDto> getProgressLogs() {
        return mediaLogService.getProgressLogs();
    }

    @GetMapping("/{bookId}/amount")
    public int getTotalProgressAmount(
            @PathVariable Long mediaId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return readingProgressService.getTotalProgressAmount(mediaId, startDate, endDate);
    }
}
