package com.example.userservicep.controller;

import com.example.userservicep.dto.AuthResponse;
import com.example.userservicep.dto.LoginRequest;
import com.example.userservicep.entity.User;
import com.example.userservicep.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // User registration
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    // User login using LoginRequest DTO and returning AuthResponse DTO
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("Login API hit");
        try {
            String token = userService.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Login failed: " + e.getMessage());
        }
    }

    // Get user by username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    // Get currently authenticated user's profile
    @GetMapping("/profile")
    public ResponseEntity<?> profile(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }
    
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from secured endpoint!");
    }
}
