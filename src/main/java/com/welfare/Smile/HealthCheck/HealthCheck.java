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

        // ✅ MongoDB check
        try {
            mongoTemplate.executeCommand("{ ping: 1 }");
            status.append("DB: OK | ");
        } catch (Exception e) {
            status.append("DB: FAIL | ");
        }

        // ✅ Config file check
        try {
            ClassPathResource resource = new ClassPathResource("config.json");
            status.append(resource.exists() ? "Config: OK | " : "Config: MISSING | ");
        } catch (Exception e) {
            status.append("Config: ERROR | ");
        }

        // ✅ API check
        try {
            ResponseEntity<String> res = restTemplate.getForEntity("http://localhost:8082/api/v1/journal", String.class);
            status.append(res.getStatusCode().is2xxSuccessful() ? "API: OK" : "API: FAIL");
        } catch (Exception e) {
            status.append("API: ERROR (" + e.getClass().getSimpleName() + ")");
        }

        return ResponseEntity.ok(status.toString());
    }
}
