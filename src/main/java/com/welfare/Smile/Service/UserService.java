package com.welfare.Smile.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final MongoTemplate userTemplate;

    // ✅ Inject the MongoTemplate for userdb using @Qualifier
    public UserService(@Qualifier("userTemplate") MongoTemplate userTemplate) {
        this.userTemplate = userTemplate;
    }

    public void saveUserData() {
        // Use userTemplate to interact with userdb
        System.out.println("Using userdb...");
    }
}
