package com.jalu.tracker.controller;

import com.jalu.tracker.entity.Book;
import com.jalu.tracker.service.BookService;
import com.jalu.tracker.service.ReadingProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReadingProgressService readingProgressService;

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @PostMapping("/update-progress")
    public ResponseEntity<String> addProgress(@RequestParam Long bookId, @RequestParam int charactersRead) {
        String responseMessage = readingProgressService.addProgress(bookId, charactersRead);

        if (responseMessage.equals("Book not found")) {
            return ResponseEntity.badRequest().body(responseMessage);
        }

        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/{bookId}/characters-read")
    public int getTotalCharactersRead(
            @PathVariable Long bookId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return readingProgressService.getTotalCharactersRead(bookId, startDate, endDate);
    }
}
