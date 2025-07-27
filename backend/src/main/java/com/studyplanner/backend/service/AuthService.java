package com.studyplanner.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.studyplanner.backend.model.User;
import com.studyplanner.backend.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(User user) {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            System.out.println("Registration failed: Email already exists");
            return null;
        }

        // Encode password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User savedUser = userRepo.save(user);
        System.out.println("User registered: " + savedUser.getEmail());
        return savedUser;
    }

   public User login(String email, String password) {
    User user = userRepo.findByEmail(email);
    if (user == null) {
        System.out.println("Login failed: User not found for email = " + email);
        return null;
    }

    System.out.println("Stored hash: " + user.getPassword());
    System.out.println("Trying password: " + password);

    if (!passwordEncoder.matches(password, user.getPassword())) {
        System.out.println("Login failed: Incorrect password for email = " + email);
        return null;
    }

    System.out.println("Login successful for " + email);
    return user;
}

}
