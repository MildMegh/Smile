package com.welfare.Smile.controller;

import com.welfare.Smile.model.JournalEntry;
import com.welfare.Smile.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {

    private final JournalService journalService;

    @Autowired
    @Qualifier("journalTemplate")
    private MongoTemplate journalTemplate;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping("/add")
    public JournalEntry addJournalEntry(@RequestBody JournalEntry entry) {
        return journalService.addEntry(entry);
    }

    @PostMapping("/addAll")
    public List<JournalEntry> addMultipleEntries(@RequestBody List<JournalEntry> entries) {
        return journalService.addAllEntries(entries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> getEntry(@PathVariable String id) {
        return journalService.getEntry(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<JournalEntry> getAllEntries() {
        return journalService.getAllEntries();
    }

    @GetMapping("/")
    public String home() {
        return "Journal MongoDB API is up and running!";
    }
}
