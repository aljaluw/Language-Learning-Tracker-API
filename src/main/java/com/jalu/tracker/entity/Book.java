package com.jalu.tracker.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;
    private String difficultyRating;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ReadingProgress> readingProgressList = new ArrayList<>();

    public Book(Long id, String title, String author, String genre, String difficultyRating, List<ReadingProgress> readingProgressList) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.difficultyRating = difficultyRating;
        this.readingProgressList = readingProgressList;
    }

    public Book() {

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(String difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public List<ReadingProgress> getReadingProgressList() {
        return readingProgressList;
    }

    public void setReadingProgressList(List<ReadingProgress> readingProgressList) {
        this.readingProgressList = readingProgressList;
    }

    public void addReadingProgress(ReadingProgress progress) {
        this.readingProgressList.add(progress);
        progress.setBook(this);
    }
}
