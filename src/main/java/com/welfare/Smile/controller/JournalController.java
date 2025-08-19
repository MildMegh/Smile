package com.welfare.Smile.controller;

import com.welfare.Smile.mongo.model.JournalEntry;
import com.welfare.Smile.mongo.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
@RestController
@RequestMapping("/api/journals") // 🔹 Changed to /api/journals for consistency
public class JournalController {

    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping("/add")
    public JournalEntry addEntry(@RequestBody JournalEntry entry) {
        return journalService.saveEntry(entry);
    }

    @GetMapping("/all")
    public List<JournalEntry> getAll() {
        return journalService.getAllEntries();
    }

    @GetMapping("/{id}")
    public Optional<JournalEntry> getById(@PathVariable String id) {
        return journalService.getEntryById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable String id, @RequestBody JournalEntry updatedEntry) {
        Optional<JournalEntry> existingEntry = journalService.getEntryById(id);
        if (existingEntry.isPresent()) {
            JournalEntry entry = existingEntry.get();
            entry.setTitle(updatedEntry.getTitle());
            entry.setContent(updatedEntry.getContent());
            journalService.saveEntry(entry);
            return ResponseEntity.ok(entry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return journalService.deleteEntry(id);
    }

    @GetMapping("/search/title")
    public List<JournalEntry> searchByTitle(@RequestParam String keyword) {
        return journalService.searchByTitle(keyword);
    }

    @GetMapping("/search/content")
    public List<JournalEntry> searchByContent(@RequestParam String keyword) {
        return journalService.searchByContent(keyword);
    }

    @PostMapping("/seed")
    public void insertSampleEntries() {
        journalService.insertSampleEntries();
    }
}
