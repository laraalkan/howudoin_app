//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.controller;

import com.example.demo.dto.FriendRequestDTO;
import com.example.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/friends"})
public class FriendController {
    @Autowired
    private FriendService friendService;

    public FriendController() {
    }

    @PostMapping({"/add"})
    public ResponseEntity<?> sendFriendRequest(@RequestBody FriendRequestDTO request) {
        String requesterEmail = request.getRequesterEmail();
        String recipientEmail = request.getRecipientEmail();
        return this.friendService.sendFriendRequest(requesterEmail, recipientEmail);
    }

    @PostMapping({"/accept"})
    public ResponseEntity<?> acceptFriendRequest(@RequestBody FriendRequestDTO request) {
        return this.friendService.acceptFriendRequest(request.getRequesterEmail(), request.getRecipientEmail());
    }
    @PostMapping("/decline")
    public ResponseEntity<?> declineFriendRequest(@RequestBody FriendRequestDTO request) {
        return friendService.declineFriendRequest(request.getRequesterEmail(), request.getRecipientEmail());
    }


    @GetMapping({"/{userEmail}"})
    public ResponseEntity<?> getFriendList(@PathVariable String userEmail) {
        return this.friendService.getFriendList(userEmail);
    }

    @GetMapping({"/pending/{userEmail}"})
    public ResponseEntity<?> getPendingFriendRequests(@PathVariable String userEmail) {
        return this.friendService.getPendingFriendRequests(userEmail);
    }
}
