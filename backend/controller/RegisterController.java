package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register") // Handle registration at "/register"
public class RegisterController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = authService.register(user);
        return ResponseEntity.ok("User registered successfully: " + registeredUser.getEmail());
    }
}
