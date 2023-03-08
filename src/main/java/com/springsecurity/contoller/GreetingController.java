package com.springsecurity.contoller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greetings")
public class GreetingController {

	
	
	@GetMapping("/hello")
	public ResponseEntity<Map<String, String>> sayHello(){
		Map<String, String> greetingMessage = new HashMap<>();
		greetingMessage.put("message","Hello Java");
		return new ResponseEntity<Map<String,String>>(greetingMessage, HttpStatus.OK);
	}
	
	@GetMapping("/namaste")
	public ResponseEntity<Map<String, String>> sayNamaste(){
		Map<String, String> greetingMessage = new HashMap<>();
		greetingMessage.put("message","Namaste !!!");
		return new ResponseEntity<Map<String,String>>(greetingMessage, HttpStatus.OK);
	}
	
	@GetMapping("/ask-for-health")
	public ResponseEntity<Map<String, String>> askForHealth(){
		Map<String, String> greetingMessage = new HashMap<>();
		greetingMessage.put("message","How are you??");
		return new ResponseEntity<Map<String,String>>(greetingMessage, HttpStatus.OK);
	}
}
