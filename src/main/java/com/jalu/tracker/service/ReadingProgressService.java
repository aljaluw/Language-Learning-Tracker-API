package com.jalu.tracker.service;

import com.jalu.tracker.entity.Book;
import com.jalu.tracker.entity.ReadingProgress;
import com.jalu.tracker.repository.BookRepository;
import com.jalu.tracker.repository.ReadingProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReadingProgressService {

    @Autowired
    private ReadingProgressRepository readingProgressRepository;

    @Autowired
    private BookRepository bookRepository;

    public String addProgress(Long bookId, int charactersRead) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isEmpty()) {
            return "Book not found";
        }

        Book book = bookOptional.get();
        ReadingProgress progress = new ReadingProgress();
        progress.setBook(book);
        progress.setDate(LocalDate.now());
        progress.setCharactersRead(charactersRead);

        readingProgressRepository.save(progress);

        return "Progress added successfully";
    }

    public int getTotalCharactersRead(Long bookId, LocalDate startDate, LocalDate endDate) {
        List<ReadingProgress> progressList = readingProgressRepository.findByBookIdAndDateBetween(bookId, startDate, endDate);

        // Sum up the characters read within the specified date range
        return progressList.stream()
                .mapToInt(ReadingProgress::getCharactersRead)
                .sum();
    }
}
