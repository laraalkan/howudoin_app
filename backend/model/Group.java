package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "groups")
public class Group {
    @Id
    private String id;
    private String name;
    private Set<String> members; // User IDs of group members
    private Set<String> messages; // Message IDs sent in the group

    // Constructors
    public Group() {
        this.members = new HashSet<>();
        this.messages = new HashSet<>();
    }

    public Group(String name) {
        this.name = name;
        this.members = new HashSet<>();
        this.messages = new HashSet<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(Set<String> members) {
        this.members = members;
    }

    public Set<String> getMessages() {
        return messages;
    }


}
