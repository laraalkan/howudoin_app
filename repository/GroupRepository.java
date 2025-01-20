package com.example.demo.repository;

import com.example.demo.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
    Set<Group> findByMembers(String email);  // Find groups that contain the given member's email
}


