package com.studyplanner.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyplanner.backend.model.User;
import com.studyplanner.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") 
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        User registeredUser = authService.register(user);
        if (registeredUser == null) {
            return ResponseEntity.badRequest().body("Registration failed: Email already exists");
        }
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
public ResponseEntity<User> login(@RequestBody User user) {
    User loggedInUser = authService.login(user.getEmail(), user.getPassword());
    if (loggedInUser == null) {
        return ResponseEntity.status(401).build(); // Unauthorized
    }
    return ResponseEntity.ok(loggedInUser); // Return user JSON (with ID)
}

}
