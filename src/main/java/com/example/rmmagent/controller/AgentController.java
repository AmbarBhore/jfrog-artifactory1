package com.example.rmmagent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {
	    @GetMapping("/info")
	        public ResponseEntity<String> getInfo() {
			        return ResponseEntity.ok("RMM Agent v3.1 - Spring Boot App. Powered by Jenkins & Kubernetes.");
		}
}
