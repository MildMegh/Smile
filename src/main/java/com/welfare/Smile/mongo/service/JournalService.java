package com.welfare.Smile.mongo.service;

import com.welfare.Smile.mongo.model.JournalEntry;
import com.welfare.Smile.mongo.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    private final JournalRepository journalRepository;
    @Autowired
    public JournalService (JournalRepository journalRepository) {
        this.journalRepository = journalRepository;

    //@Autowired
    //public void setRepository(JournalRepository journalRepository) {
    //    this.journalRepository = journalRepository;
    }

    // 1. Create / Save
    public JournalEntry saveEntry(JournalEntry entry) {
        return journalRepository.save(entry);
    }

    // 2. Read All
    public List<JournalEntry> getAllEntries() {
        return journalRepository.findAll();
    }

    // 3. Read by ID
    public Optional<JournalEntry> getEntryById(String id) {
        return journalRepository.findById(id);
    }

    // 4. Update
    public JournalEntry updateEntry(String id, JournalEntry updatedEntry) {
        if (journalRepository.existsById(id)) {
            updatedEntry.setId(id);
            return journalRepository.save(updatedEntry);
        }
        return null;
    }

    // 5. Delete by ID
    public boolean deleteEntry(String id) {
        if (journalRepository.existsById(id)) {
            journalRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 6. Search by Title
    public List<JournalEntry> searchByTitle(String keyword) {
        return journalRepository.findByTitleContainingIgnoreCase(keyword);
    }

    // 7. Search by Content
    public List<JournalEntry> searchByContent(String keyword) {
        return journalRepository.findByContentContainingIgnoreCase(keyword);
    }

    // 8. Check if entry exists
    public boolean exists(String id) {
        return journalRepository.existsById(id);
    }

    // 9. Count all entries
    public long countAll() {
        return journalRepository.count();
    }

    // 10. Delete all
    public void deleteAll() {
        journalRepository.deleteAll();
    }

    // 11. Insert sample entries
    public void insertSampleEntries() {
        List<JournalEntry> entries = List.of(
                new JournalEntry("Morning Routine", "Woke up early, went jogging."),
                new JournalEntry("Work Log", "Fixed bugs and deployed backend service."),
                new JournalEntry("Lunch", "Had grilled chicken."),
                new JournalEntry("Evening", "Watched a movie."),
                new JournalEntry("Reflection", "Reviewed the day’s progress.")
        );
        journalRepository.saveAll(entries);
    }
}
