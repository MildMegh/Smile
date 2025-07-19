package com.welfare.Smile.HealthCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HealthCheck {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        StringBuilder status = new StringBuilder();

        // ✅ Database (MongoDB) check
        try {
            mongoTemplate.executeCommand("{ ping: 1 }");
            status.append("DB: OK | ");
        } catch (Exception e) {
            status.append("DB: FAIL | ");
        }

        // ✅ Config file check (from classpath)
        try {
            ClassPathResource resource = new ClassPathResource("config.json");
            if (resource.exists()) {
                status.append("Config: OK | ");
            } else {
                status.append("Config: MISSING | ");
            }
        } catch (Exception e) {
            status.append("Config: ERROR | ");
        }

        // ✅ External API check (your own API on localhost)
        try {
            // You can change the URL to an existing endpoint from your app, such as /journals
            ResponseEntity<String> res = restTemplate.getForEntity("http://localhost:8082/journals", String.class);
            status.append(res.getStatusCode().is2xxSuccessful() ? "API: OK" : "API: FAIL");
        } catch (Exception e) {
            status.append("API: ERROR (" + e.getClass().getSimpleName() + ")");
            e.printStackTrace();
        }

        return ResponseEntity.ok(status.toString());
    }
}
