package com.example.rmmagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	    @GetMapping("/health")
	        public String healthCheck() {
			        return "RMM Agent v3.1 is up and running!";
				    }
}
