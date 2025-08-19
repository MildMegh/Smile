package com.welfare.Smile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.welfare.Smile.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
}
