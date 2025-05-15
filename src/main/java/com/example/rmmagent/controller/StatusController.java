package com.example.rmmagent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

	    @GetMapping("/status")
	        public ResponseEntity<String> getStatus() {
			        return ResponseEntity.ok("RMM Agent is running smoothly. All systems operational.");
				    }
}
