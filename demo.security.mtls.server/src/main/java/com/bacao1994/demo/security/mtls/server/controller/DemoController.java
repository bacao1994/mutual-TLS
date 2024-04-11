package com.bacao1994.demo.security.mtls.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/engine")
public class DemoController {

    @PostMapping("/start")
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok("Server successfully called!");
    }
}
