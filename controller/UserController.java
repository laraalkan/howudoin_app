package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;  // Import HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); // Return 201 Created
    }

    /*@PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }*/

}
