package com.jalu.tracker.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jalu.tracker.enums.MediaType;
import com.jalu.tracker.enums.MediaUnit;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MediaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private MediaType mediaType;

    @Column
    private int totalAmount;

    @Column(nullable = false)
    private MediaUnit unit;

    @Column
    private LocalDate startDate = LocalDate.now();

    @Column(length = 500)
    private String comment;

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MediaProgress> progressEntries = new ArrayList<>();

    public MediaLog() {

    }

    public MediaLog(Long id, String title, MediaType mediaType, int totalAmount, MediaUnit unit, LocalDate startDate, String comment, List<MediaProgress> progressEntries) {
        this.id = id;
        this.title = title;
        this.mediaType = mediaType;
        this.totalAmount = totalAmount;
        this.unit = unit;
        this.startDate = startDate;
        this.comment = comment;
        this.progressEntries = progressEntries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
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

    public List<MediaProgress> getProgressEntries() {
        return progressEntries;
    }

    public void setProgressEntries(List<MediaProgress> progressEntries) {
        this.progressEntries = progressEntries;
    }
}
