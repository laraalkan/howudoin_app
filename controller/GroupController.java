package com.example.demo.controller;

import com.example.demo.model.Group;
import com.example.demo.service.GroupService;
import com.example.demo.service.UserService;
import com.example.demo.dto.GroupCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final UserService userService;  // Inject UserService


    @Autowired
    
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
        userService = null;
    }

    /*@PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody GroupCreationRequest request) {
        Group group = groupService.createGroup(request.getName(), request.getMembers());
        return ResponseEntity.ok(group);
    }*/
    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody GroupCreationRequest groupRequest) {
        // Use the emails directly for group creation
        return groupService.createGroup(groupRequest.getName(), groupRequest.getMembers());
    }



    @PostMapping("/{groupId}/add-member")
    public ResponseEntity<?> addGroupMember(@PathVariable String groupId, @RequestBody Map<String, String> request) {
        String memberId = request.get("memberId");
        if (memberId == null || memberId.isEmpty()) {
            return ResponseEntity.badRequest().body("Required parameter 'memberId' is not present.");
        }
        return groupService.addGroupMember(groupId, memberId);
    }


    @PostMapping("/{groupId}/send")
    public ResponseEntity<?> sendMessageToGroup(@PathVariable String groupId, @RequestBody Map<String, String> requestBody) {
        String messageId = requestBody.get("messageId");
        return groupService.sendMessageToGroup(groupId, messageId);
    }

    @GetMapping("/{groupId}/messages")
    public ResponseEntity<Set<String>> getGroupMessages(@PathVariable String groupId) {
        return groupService.getGroupMessages(groupId);
    }

    @GetMapping("/{groupId}/members")
    public ResponseEntity<Set<String>> getGroupMembers(@PathVariable String groupId) {
        return groupService.getGroupMembers(groupId);
    }
    @GetMapping("/")
    public ResponseEntity<Set<Group>> getAllGroups() {
        Set<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }
    @GetMapping("/user/{userEmail}")
    public ResponseEntity<Set<Group>> getUserGroups(@PathVariable String userEmail) {
        return groupService.getGroupsForUser(userEmail);  // Fetch groups the logged-in user is part of
    }



}
