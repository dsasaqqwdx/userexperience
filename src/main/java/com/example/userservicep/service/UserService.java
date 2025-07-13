package com.example.userservicep.service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.example.userservicep.entity.User;
import com.example.userservicep.repository.UserRepository;
import com.example.userservicep.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import javax.naming.AuthenticationException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        System.out.println("Encoded password: " + encodedPassword);
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public String login(String username, String password) throws Exception {
        System.out.println("Trying to authenticate: " + username + " / " + password);
        
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            System.out.println("✅ User found");
            System.out.println("➡️ DB Encoded password: " + user.getPassword());
            System.out.println("➡️ Raw password: " + password);
            boolean match = passwordEncoder.matches(password, user.getPassword());
            System.out.println("✅ Password match: " + match);
        } else {
            System.out.println("❌ User not found in DB");
        }

        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println("✅ Spring Security authentication success");
            return jwtUtil.generateToken(username);
        } catch (BadCredentialsException e) {
            System.out.println("❌ Spring Security authentication failed: " + e.getMessage());
            throw new Exception("Invalid credentials", e);
        }
    }


   


    }

    

