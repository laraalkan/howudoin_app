package com.example.demo.dto;
public class FriendRequestDTO {
    private String requesterEmail; // Email of the user sending the request
    private String recipientEmail; // Email of the user receiving the request

    // Default constructor
    public FriendRequestDTO() {}

    // Constructor for convenience
    public FriendRequestDTO(String requesterEmail, String recipientEmail) {
        this.requesterEmail = requesterEmail;
        this.recipientEmail = recipientEmail;
    }

    // Getters and Setters
    public String getRequesterEmail() {
        return requesterEmail;
    }

    public void setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }
}
/*public class FriendRequestDTO {
    private String senderId;   // User who sends the friend request
    private String receiverId; // User who receives the friend request
    private boolean accepted;  // Status of the friend request (accepted or not)

    // Getters and Setters
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
*/
/*package com.example.demo.dto;

public class FriendRequestDTO {
    private String requesterId;
    private String recipientId;

    // Getters and Setters
    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }
}*/
