package com.jalu.tracker.controller;

import com.jalu.tracker.dto.MediaLogProgressRequestDto;
import com.jalu.tracker.entity.MediaLog;
import com.jalu.tracker.service.MediaLogService;
import com.jalu.tracker.service.ReadingProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping
    public List<MediaLog> getAllMedia() {
        return mediaLogService.findAll();
    }

    @PostMapping("/log")
    public ResponseEntity<String> logMediaProgress(@RequestBody MediaLogProgressRequestDto request) {
        String response = mediaLogService.addMediaLog(request);

        if (response.startsWith("Unit mismatch!")) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{bookId}/amount")
    public int getTotalProgressAmount(
            @PathVariable Long mediaId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return readingProgressService.getTotalProgressAmount(mediaId, startDate, endDate);
    }
}
