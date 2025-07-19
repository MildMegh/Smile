package com.welfare.Smile.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class UserMongoConfig {

    @Bean(name = "userMongoClient")
    public MongoClient userMongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean(name = "userTemplate")
    public MongoTemplate userTemplate(@Qualifier("userMongoClient") MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "userdb");
    }
}
