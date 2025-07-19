package com.welfare.Smile.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final MongoTemplate userTemplate;

    public UserController(@Qualifier("userTemplate") MongoTemplate userTemplate) {
        this.userTemplate = userTemplate;
    }

    @GetMapping("/users/test")
    public String testUserDb() {
        long count = userTemplate.getCollection("users").countDocuments(); // Example
        return "User collection count: " + count;
    }
}
