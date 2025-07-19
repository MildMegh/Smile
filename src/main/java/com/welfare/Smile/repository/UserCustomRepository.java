package com.welfare.Smile.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserCustomRepository {

    private final MongoTemplate userTemplate;

    public UserCustomRepository(@Qualifier("userTemplate") MongoTemplate userTemplate) {
        this.userTemplate = userTemplate;
    }

    public void doSomething() {
        // Example: Insert test data
        org.bson.Document doc = new org.bson.Document("name", "Megh");
        userTemplate.getCollection("users").insertOne(doc);
    }
}
