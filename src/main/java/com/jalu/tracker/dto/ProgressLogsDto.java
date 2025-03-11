package com.jalu.tracker.dto;

import com.jalu.tracker.enums.MediaType;
import com.jalu.tracker.enums.MediaUnit;

import java.time.LocalDate;

public class ProgressLogsDto {

    private LocalDate date;
    private MediaType mediaType;
    private String title;
    private Integer amount;
    private MediaUnit unit;

    public ProgressLogsDto() {

    }

    public ProgressLogsDto(LocalDate date, MediaType mediaType, String title, Integer amount, MediaUnit unit) {
        this.date = date;
        this.mediaType = mediaType;
        this.title = title;
        this.amount = amount;
        this.unit = unit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
}
