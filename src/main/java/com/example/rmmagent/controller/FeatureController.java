package com.example.rmmagent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureController {

	    @GetMapping("/features")
	        public ResponseEntity<String> getFeatureInfo() {
			        return ResponseEntity.ok("🆕 New Feature Enabled in RMM Agent v3.2");
				    }
}
