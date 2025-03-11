package com.jalu.tracker.dto;

import com.jalu.tracker.enums.MediaType;
import com.jalu.tracker.enums.MediaUnit;

import java.time.LocalDate;

public class MediaResponseDto {

    private MediaType mediaType;

    private String title;

    private Integer totalAmount;

    private MediaUnit unit;

    private LocalDate startDate;

    private String comment;

    public MediaResponseDto() {

    }

    public MediaResponseDto(MediaType mediaType, String title, Integer totalAmount, MediaUnit unit, LocalDate startDate, String comment) {
        this.mediaType = mediaType;
        this.title = title;
        this.totalAmount = totalAmount;
        this.unit = unit;
        this.startDate = startDate;
        this.comment = comment;
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

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public MediaUnit getUnit() {
        return unit;
    }

    public void setUnit(MediaUnit unit) {
        this.unit = unit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
