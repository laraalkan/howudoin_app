package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "friendships")
public class Friendship {

    @Id
    private String id;
    private String senderId;     // User who sends the friend request
    private String receiverId;   // User who receives the friend request
    private boolean accepted;    // Status of the friend request

    // Constructors
    public Friendship() {}

    public Friendship(String senderId, String receiverId, boolean accepted) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.accepted = accepted;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
