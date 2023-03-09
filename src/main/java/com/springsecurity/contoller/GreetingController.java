package com.springsecurity.contoller;

import java.util.HashMap;
import java.util.Map;

import com.springsecurity.dto.AuthRequest;
import com.springsecurity.entities.User;
import com.springsecurity.service.JwtService;
import com.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/greetings")
public class GreetingController {

    @Autowired
    private UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManger;


    @GetMapping("/hello")
    public ResponseEntity<Map<String, String>> sayHello() {
        Map<String, String> greetingMessage = new HashMap<>();
        greetingMessage.put("message", "Hello Java");
        return new ResponseEntity<Map<String, String>>(greetingMessage, HttpStatus.OK);
    }

    @GetMapping("/namaste")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Map<String, String>> sayNamaste() {
        Map<String, String> greetingMessage = new HashMap<>();
        greetingMessage.put("message", "Namaste !!!");
        return new ResponseEntity<Map<String, String>>(greetingMessage, HttpStatus.OK);
    }

    @GetMapping("/ask-for-health")
    public ResponseEntity<Map<String, String>> askForHealth() {
        Map<String, String> greetingMessage = new HashMap<>();
        greetingMessage.put("message", "How are you??");
        return new ResponseEntity<Map<String, String>>(greetingMessage, HttpStatus.OK);
    }

    @PostMapping("/save-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = this.userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }else{
            throw new UsernameNotFoundException("Invalid User !!!");
        }

    }
}
