package com.edwin.textprocessorapi.controllers.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadinessController {

    @GetMapping("/health/ready")
    public ResponseEntity<String> readiness() {
        return ResponseEntity.ok("✅ Readiness check OK");
    }

}
