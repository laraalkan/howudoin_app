package com.example.demo.service;

import com.example.demo.model.Message;
import com.example.demo.model.Friendship;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;


@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final FriendshipRepository friendshipRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, FriendshipRepository friendshipRepository) {
        this.messageRepository = messageRepository;
        this.friendshipRepository = friendshipRepository;
    }

    /*public ResponseEntity<?> sendMessage(Message message) {
        String senderId = message.getSenderId();
        String recipientId = message.getRecipientId();

        // Validate if sender and recipient are friends using the new query
        List<Friendship> friendships = friendshipRepository.findAcceptedFriendships(senderId, recipientId);

        if (friendships.isEmpty()) {
            return ResponseEntity.status(403).body("Users are not friends. Cannot send a message.");
        }

        // Save the message to the database
        messageRepository.save(message);
        return ResponseEntity.ok("Message sent successfully!");
    }*/
    /*public ResponseEntity<?> getConversationHistory(String userId1, String userId2) {
        // Retrieve messages between two users
        List<Message> conversation = messageRepository.findBySenderIdAndRecipientIdOrRecipientIdAndSenderId(userId1, userId2, userId2, userId1);

        if (conversation.isEmpty()) {
            return ResponseEntity.status(404).body("No conversation history found between these users.");
        }

        return ResponseEntity.ok(conversation);
    }*/
    public ResponseEntity<?> getConversationHistory(String userId1, String userId2) {
        // Retrieve messages where user1 is the sender and user2 is the recipient
        List<Message> conversationUser1ToUser2 = messageRepository.findBySenderIdAndRecipientId(userId1, userId2);

        // Retrieve messages where user2 is the sender and user1 is the recipient
        List<Message> conversationUser2ToUser1 = messageRepository.findBySenderIdAndRecipientId(userId2, userId1);

        // Combine both conversations into one list
        List<Message> allMessages = new ArrayList<>();
        allMessages.addAll(conversationUser1ToUser2);
        allMessages.addAll(conversationUser2ToUser1);

        // Sort by timestamp and then by ID for consistent ordering
        allMessages.sort(Comparator
                .comparing(Message::getTimestamp) // Primary sorting by timestamp
                .thenComparing(Message::getId));  // Secondary sorting by unique ID

        if (allMessages.isEmpty()) {
            return ResponseEntity.status(404).body("No conversation history found between these users.");
        }

        return ResponseEntity.ok(allMessages);
    }





    /*public ResponseEntity<?> getConversationHistory(String userId1, String userId2) {
        // Retrieve messages between two users
        List<Message> conversation = messageRepository.findBySenderIdAndRecipientIdOrRecipientIdAndSenderId(userId1, userId2, userId1, userId2);
        return ResponseEntity.ok(conversation);
    }*/

    public ResponseEntity<?> getGroupMessages(String groupId) {
        // Retrieve messages for a specific group
        List<Message> groupMessages = messageRepository.findByGroupId(groupId);
        return ResponseEntity.ok(groupMessages);
    }

    public List<Message> getAllMessages() {
        // Retrieve all messages
        return messageRepository.findAll();
    }
}

/*package com.example.demo.service;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Send a message
    public ResponseEntity<?> sendMessage(Message message) {
        message.setTimestamp(System.currentTimeMillis());
        messageRepository.save(message);
        return ResponseEntity.ok("Message sent successfully!");
    }

    // Retrieve conversation history between two users
    public ResponseEntity<List<Message>> getConversationHistory(String userId1, String userId2) {
        List<Message> messages = messageRepository.findBySenderIdAndRecipientId(userId1, userId2);
        messages.addAll(messageRepository.findBySenderIdAndRecipientId(userId2, userId1));
        return ResponseEntity.ok(messages);
    }

    // Retrieve group message history
    public ResponseEntity<List<Message>> getGroupMessages(String groupId) {
        List<Message> messages = messageRepository.findByGroupId(groupId);
        return ResponseEntity.ok(messages);
    }
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

}*/
