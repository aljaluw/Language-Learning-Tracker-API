package com.jalu.tracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ReadingProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private int charactersRead;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Book book;

    public ReadingProgress(Long id, LocalDate date, int charactersRead, Book book) {
        this.id = id;
        this.date = date;
        this.charactersRead = charactersRead;
        this.book = book;
    }

    public ReadingProgress() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCharactersRead() {
        return charactersRead;
    }

    public void setCharactersRead(int charactersRead) {
        this.charactersRead = charactersRead;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
