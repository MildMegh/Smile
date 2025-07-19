package com.welfare.Smile.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    // Default MongoClient
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    // Default MongoTemplate bean — this is what Spring is looking for
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "journaldb");
    }

    // Custom journalTemplate (optional if above is enough)
    @Bean(name = "journalMongoClient")
    public MongoClient journalMongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean(name = "journalTemplate")
    public MongoTemplate journalTemplate(@Qualifier("journalMongoClient") MongoClient client) {
        return new MongoTemplate(client, "journaldb");
    }

    // Add more templates for userdb, logdb, etc. similarly
}
