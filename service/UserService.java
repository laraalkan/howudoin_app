package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll(); // Return all users from DB
    }

    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id); // Retrieve user by ID
        return user.orElse(null); // Return user or null if not found
    }

    public User createUser(User user) {
        return userRepository.save(user);  // Ensure the user is saved properly
    }
}
