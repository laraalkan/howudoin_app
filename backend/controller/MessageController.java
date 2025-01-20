package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/*@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;


    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/conversation")
    public ResponseEntity<?> getConversationHistory(@RequestParam String userId1, @RequestParam String userId2) {
        return messageService.getConversationHistory(userId1, userId2);
    }

    @GetMapping("/group")
    public ResponseEntity<?> getGroupMessages(@RequestParam String groupId) {
        return messageService.getGroupMessages(groupId);
    }
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }
}*/
