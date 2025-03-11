package com.jalu.tracker.dto;

import com.jalu.tracker.enums.MediaType;

public class MediaListDto {

    private Long id;
    private MediaType mediaType;
    private String title;

    public MediaListDto() {

    }
    public MediaListDto(Long id, MediaType mediaType, String title) {
        this.id = id;
        this.mediaType = mediaType;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
