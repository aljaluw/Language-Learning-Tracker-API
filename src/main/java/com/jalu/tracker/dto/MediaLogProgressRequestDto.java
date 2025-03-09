package com.jalu.tracker.dto;

import com.jalu.tracker.enums.MediaType;
import com.jalu.tracker.enums.MediaUnit;

import java.time.LocalDate;

public class MediaLogProgressRequestDto {

    private Long mediaId;
    private MediaType mediaType;
    private String title;
    private Integer amount;
    private MediaUnit unit;
    private LocalDate date;
    private String comment;

    // Default constructor (for JSON serialization)
    public MediaLogProgressRequestDto() {}

    public MediaLogProgressRequestDto(Long mediaId, MediaType mediaType, String title, Integer amount, MediaUnit unit, LocalDate date, String comment) {
        this.mediaId = mediaId;
        this.mediaType = mediaType;
        this.title = title;
        this.amount = amount;
        this.unit = unit;
        this.date = date;
        this.comment = comment;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public MediaUnit getUnit() {
        return unit;
    }

    public void setUnit(MediaUnit unit) {
        this.unit = unit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
