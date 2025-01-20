package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user using the login method
            User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(user); // Return the authenticated user details
        } catch (Exception ex) {
            // Handle the exception and return an appropriate response
            return ResponseEntity.status(400).body("Error: " + ex.getMessage());
        }
    }
}

/* BUUpackage com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user and generate JWT token
            String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok("JWT Token: " + token);
        } catch (Exception ex) {
            // Handle the exception and return an appropriate response
            return ResponseEntity.status(400).body("Error: " + ex.getMessage());
        }
    }


}
*/
