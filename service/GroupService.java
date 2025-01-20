package com.example.demo.service;

import com.example.demo.model.Group;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;  // Add this field

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;  // Initialize the UserRepository
    }

    public ResponseEntity<String> createGroup(String name, Set<String> emails) {
        Set<String> validEmails = new HashSet<>();

        // Check if the emails belong to valid users
        for (String email : emails) {
            if (userRepository.findByEmail(email).isPresent()) {
                validEmails.add(email);
            } else {
                return ResponseEntity.badRequest().body("Invalid email: " + email);
            }
        }

        Group group = new Group();
        group.setName(name);
        group.setMembers(validEmails); // Use valid emails only

        groupRepository.save(group);
        return ResponseEntity.ok("Group \"" + name + "\" created successfully!");
    }



    // Add a member to an existing group
    public ResponseEntity<?> addGroupMember(String groupId, String memberId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            group.getMembers().add(memberId);
            groupRepository.save(group);
            return ResponseEntity.ok("Member added successfully!");
        } else {
            return ResponseEntity.badRequest().body("Group not found!");
        }
    }

    // Send a message to a group
    public ResponseEntity<?> sendMessageToGroup(String groupId, String messageId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            group.getMessages().add(messageId);
            groupRepository.save(group);
            return ResponseEntity.ok("Message sent successfully to the group!");
        } else {
            return ResponseEntity.badRequest().body("Group not found!");
        }
    }

    // Retrieve messages for a group
    public ResponseEntity<Set<String>> getGroupMessages(String groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            return ResponseEntity.ok(optionalGroup.get().getMessages());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve members of a group
    public ResponseEntity<Set<String>> getGroupMembers(String groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            return ResponseEntity.ok(optionalGroup.get().getMembers());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
    public Set<Group> getAllGroups() {
        return new HashSet<>(groupRepository.findAll()); // Fetch all groups and return as a Set
    }
    public ResponseEntity<Set<Group>> getGroupsForUser(String userEmail) {
        Set<Group> groups = groupRepository.findByMembers(userEmail);  // Query groups where the user is a member
        return ResponseEntity.ok(groups);
    }


}
