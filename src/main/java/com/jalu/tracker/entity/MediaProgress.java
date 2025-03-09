package com.jalu.tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "media_progress")
public class MediaProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    @JsonIgnore
    private MediaLog media;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private LocalDate date = LocalDate.now();

    @Column(length = 500)
    private String comment;

    public MediaProgress() {

    }
    public MediaProgress(Long id, MediaLog media, int amount, LocalDate date, String comment) {
        this.id = id;
        this.media = media;
        this.amount = amount;
        this.date = date;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MediaLog getMedia() {
        return media;
    }

    public void setMedia(MediaLog media) {
        this.media = media;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
