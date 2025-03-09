package com.jalu.tracker.enums;

public enum MediaType {
    READING,
    VISUAL_NOVEL,
    MOVIE,
    TV_SHOW,
    GAME,
    LISTENING;

    public MediaUnit getDefaultUnit() {
        return switch (this) {
            case READING -> MediaUnit.CHARACTERS;  // Default, but could also be PAGES or CHAPTERS
            case VISUAL_NOVEL -> MediaUnit.CHARACTERS;
            case MOVIE, GAME, LISTENING -> MediaUnit.MINUTES;
            case TV_SHOW -> MediaUnit.EPISODES;
        };
    }
}

