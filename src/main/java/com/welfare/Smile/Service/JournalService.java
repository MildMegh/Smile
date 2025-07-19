package com.welfare.Smile.service;

import com.welfare.Smile.model.JournalEntry;
import com.welfare.Smile.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public JournalEntry addEntry(JournalEntry entry) {
        return journalRepository.save(entry);
    }

    // ✅ Correct method to save a list of journal entries
    public List<JournalEntry> addAllEntries(List<JournalEntry> entries) {
        return journalRepository.saveAll(entries);
    }

    public Optional<JournalEntry> getEntry(String id) {
        return journalRepository.findById(id);
    }

    public List<JournalEntry> getAllEntries() {
        return journalRepository.findAll();
    }
}
