package com.welfare.Smile.mongo.repository;

import com.welfare.Smile.mongo.model.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface JournalRepository extends MongoRepository<JournalEntry, String> {
    List<JournalEntry> findByTitleContainingIgnoreCase(String keyword);
    List<JournalEntry> findByContentContainingIgnoreCase(String keyword);
}
