package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String username; // User's display name
    private String email;    // User's email (used for login)
    private String password; // Hashed password

    //private Set<String> friends = new HashSet<>(); // Set of friend user emails
    //private Set<String> pendingFriendRequests = new HashSet<>(); // Emails of users who sent requests
    @JsonIgnore  // Prevent serializing friends list to avoid cyclical references
    private Set<String> friends = new HashSet<>();

    @JsonIgnore  // Prevent serializing pending friend requests to avoid cyclical references
    private Set<String> pendingFriendRequests = new HashSet<>();

    // Default Constructor
    public User() {}

    // Parameterized Constructor
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    public void addFriend(String friendEmail) {
        this.friends.add(friendEmail);
    }

    public Set<String> getPendingFriendRequests() {
        return pendingFriendRequests;
    }

    public void setPendingFriendRequests(Set<String> pendingFriendRequests) {
        this.pendingFriendRequests = pendingFriendRequests;
    }

    public void addPendingFriendRequest(String requesterEmail) {
        this.pendingFriendRequests.add(requesterEmail);
    }

    public void removePendingFriendRequest(String requesterEmail) {
        this.pendingFriendRequests.remove(requesterEmail);
    }
}
/* BU package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String username; // User's display name
    private String email;    // User's email (used for login)
    private String password; // Hashed password
    //private Set<String> friends; // Set of friend user IDs
    //private Set<String> pendingFriendRequests; // Friend requests waiting for acceptance
    //private Set<String> groups; // IDs of groups the user belongs to

    // Default Constructor

    // Parameterized Constructor
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 */


    /*public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    public Set<String> getPendingFriendRequests() {
        return pendingFriendRequests;
    }

    public void setPendingFriendRequests(Set<String> pendingFriendRequests) {
        this.pendingFriendRequests = pendingFriendRequests;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }

    // Utility Methods
    public void addFriend(String friendId) {
        this.friends.add(friendId);
    }

    public void addPendingFriendRequest(String userId) {
        this.pendingFriendRequests.add(userId);
    }

    public void joinGroup(String groupId) {
        this.groups.add(groupId);
    }

    public void removePendingFriendRequest(String userId) {
        this.pendingFriendRequests.remove(userId);
    }
}*/
