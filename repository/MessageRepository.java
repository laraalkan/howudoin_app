package com.example.demo.repository;

import com.example.demo.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    // Fetch direct messages where sender is user1 and recipient is user2
    List<Message> findBySenderIdAndRecipientId(String senderId, String recipientId);



    // Custom method to find messages by groupId
    List<Message> findByGroupId(String groupId);
}



