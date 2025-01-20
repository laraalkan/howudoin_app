package com.example.demo.service;

import com.example.demo.dto.FriendRequestDTO;
import com.example.demo.model.Friendship;
import com.example.demo.model.User;
import com.example.demo.repository.FriendshipRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendService {

    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    @Autowired
    public FriendService(UserRepository userRepository, FriendshipRepository friendshipRepository) {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
    }

    // Send friend request
    public ResponseEntity<?> sendFriendRequest(String requesterEmail, String recipientEmail) {
        Optional<User> requesterOpt = userRepository.findByEmail(requesterEmail);
        Optional<User> recipientOpt = userRepository.findByEmail(recipientEmail);

        if (requesterOpt.isEmpty() || recipientOpt.isEmpty()) {
            return ResponseEntity.status(404).body("One or both users not found.");
        }

        User requester = requesterOpt.get();
        User recipient = recipientOpt.get();

        // Check if a friendship already exists
        Optional<Friendship> existingFriendship = friendshipRepository.findBySenderIdAndReceiverId(requester.getId(), recipient.getId());
        if (existingFriendship.isPresent()) {
            return ResponseEntity.status(400).body("Friend request already exists.");
        }

        // Create and save new friendship
        Friendship friendship = new Friendship(requester.getId(), recipient.getId(), false);
        friendshipRepository.save(friendship);

        return ResponseEntity.ok("Friend request sent successfully!");
    }

    // Accept friend request
    public ResponseEntity<?> acceptFriendRequest(String requesterEmail, String recipientEmail) {
        Optional<User> requesterOpt = userRepository.findByEmail(requesterEmail);
        Optional<User> recipientOpt = userRepository.findByEmail(recipientEmail);

        if (requesterOpt.isEmpty() || recipientOpt.isEmpty()) {
            return ResponseEntity.status(404).body("One or both users not found.");
        }

        User requester = requesterOpt.get();
        User recipient = recipientOpt.get();

        // Find pending friend request
        Optional<Friendship> friendshipOpt = friendshipRepository.findBySenderIdAndReceiverId(requester.getId(), recipient.getId());
        if (friendshipOpt.isEmpty() || friendshipOpt.get().isAccepted()) {
            return ResponseEntity.status(404).body("No pending friend request found.");
        }

        // Update the friendship as accepted
        Friendship friendship = friendshipOpt.get();
        friendship.setAccepted(true);
        friendshipRepository.save(friendship);

        return ResponseEntity.ok("Friend request accepted successfully!");
    }

    // Get friend list
    public ResponseEntity<?> getFriendList(String userEmail) {
        Optional<User> userOpt = userRepository.findByEmail(userEmail);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("User not found.");
        }

        User user = userOpt.get();

        // Retrieve all accepted friendships where the user is either the sender or receiver
        List<Friendship> friendships = friendshipRepository.findBySenderIdOrReceiverIdAndAcceptedTrue(user.getId(), user.getId());

        // Map the friendships to a list of friend IDs
        List<String> friendIds = friendships.stream()
                .map(f -> f.getSenderId().equals(user.getId()) ? f.getReceiverId() : f.getSenderId())
                .collect(Collectors.toList());

        // Fetch friend user details based on the IDs
        List<User> friends = userRepository.findAllById(friendIds);

        return ResponseEntity.ok(friends);
    }

    // Get pending friend requests for the logged-in user
    public ResponseEntity<?> getPendingFriendRequests(String userEmail) {
        Optional<User> userOpt = userRepository.findByEmail(userEmail);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("User not found.");
        }

        User user = userOpt.get();

        // Find all pending friend requests where the user is the recipient
        List<Friendship> pendingRequests = friendshipRepository.findByReceiverIdAndAcceptedFalse(user.getId());

        // Map the friendships to DTOs
        List<FriendRequestDTO> pendingRequestsDTOs = pendingRequests.stream()
                .map(req -> new FriendRequestDTO(userRepository.findById(req.getSenderId()).get().getEmail(), user.getEmail()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(pendingRequestsDTOs);
    }
    public ResponseEntity<?> declineFriendRequest(String senderId, String receiverId) {
        // Find the pending friend request
        var friendshipOptional = friendshipRepository.findBySenderIdAndReceiverId(senderId, receiverId);
        if (friendshipOptional.isPresent()) {
            friendshipRepository.delete(friendshipOptional.get()); // Delete the request
            return ResponseEntity.ok("Friend request declined successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Friend request not found.");
        }
    }

}


/* BUpackage com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendService {

    private final UserRepository userRepository;

    @Autowired
    public FriendService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Send friend request based on email addresses
    public ResponseEntity<?> sendFriendRequest(String requesterEmail, String recipientEmail) {
        Optional<User> requesterOpt = userRepository.findByEmail(requesterEmail);
        Optional<User> recipientOpt = userRepository.findByEmail(recipientEmail);

        if (requesterOpt.isEmpty() || recipientOpt.isEmpty()) {
            return ResponseEntity.status(404).body("One or both users not found.");
        }

        User requester = requesterOpt.get();
        User recipient = recipientOpt.get();

        // Here you can implement logic to save the friend request to a database.
        // For example, adding a friend request document to a collection.

        return ResponseEntity.ok("Friend request sent successfully!");
    }

    // Accept friend request logic
    public ResponseEntity<?> acceptFriendRequest(String requesterEmail, String recipientEmail) {
        Optional<User> requesterOpt = userRepository.findByEmail(requesterEmail);
        Optional<User> recipientOpt = userRepository.findByEmail(recipientEmail);

        if (requesterOpt.isEmpty() || recipientOpt.isEmpty()) {
            return ResponseEntity.status(404).body("One or both users not found.");
        }

        User requester = requesterOpt.get();
        User recipient = recipientOpt.get();

        // Implement the logic to update the friend request status here.
        // This could involve modifying the requester and recipient's friend lists.

        return ResponseEntity.ok("Friend request accepted successfully!");
    }

    // Get friend list logic (based on email)
    public ResponseEntity<?> getFriendList(String userEmail) {
        Optional<User> userOpt = userRepository.findByEmail(userEmail);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("User not found.");
        }

        User user = userOpt.get();

        // Assuming `user.getFriends()` returns a list of friends
        return ResponseEntity.ok(user.getFriends());
    }
}


*/
/*package com.example.demo.service;

import com.example.demo.model.Friendship;
import com.example.demo.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendService {

    private final FriendshipRepository friendshipRepository;

    @Autowired
    public FriendService(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    // Send a friend request
    public ResponseEntity<?> sendFriendRequest(String requesterId, String recipientId) {
        // Validate input
        if (requesterId == null || requesterId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Requester ID is missing or invalid!");
        }
        if (recipientId == null || recipientId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Recipient ID is missing or invalid!");
        }

        // Prevent user from sending a request to themselves
        if (requesterId.equals(recipientId)) {
            return ResponseEntity.badRequest().body("You cannot send a friend request to yourself!");
        }

        // Check if friendship already exists in either direction
        Optional<Friendship> existingFriendship = friendshipRepository
                .findBySenderIdAndReceiverId(requesterId, recipientId)
                .or(() -> friendshipRepository.findBySenderIdAndReceiverId(recipientId, requesterId));

        if (existingFriendship.isPresent()) {
            Friendship friendship = existingFriendship.get();
            if (friendship.isAccepted()) {
                return ResponseEntity.badRequest().body("You are already friends!");
            } else {
                return ResponseEntity.badRequest().body("Friend request already sent!");
            }
        }

        // Create a new friend request
        Friendship friendship = new Friendship(requesterId, recipientId, false); // "false" for not yet accepted
        friendshipRepository.save(friendship);
        return ResponseEntity.ok("Friend request sent successfully!");
    }

    // Accept a friend request
    public ResponseEntity<?> acceptFriendRequest(String requesterId, String recipientId) {
        // Validate input
        if (requesterId == null || requesterId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Requester ID is missing or invalid!");
        }
        if (recipientId == null || recipientId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Recipient ID is missing or invalid!");
        }

        // Prevent user from accepting a request from themselves
        if (requesterId.equals(recipientId)) {
            return ResponseEntity.badRequest().body("Invalid operation: requester and recipient cannot be the same.");
        }

        // Find the friend request
        Optional<Friendship> friendshipOptional = friendshipRepository.findBySenderIdAndReceiverId(requesterId, recipientId);

        if (friendshipOptional.isPresent()) {
            Friendship friendship = friendshipOptional.get();

            if (friendship.isAccepted()) {
                return ResponseEntity.badRequest().body("Friend request has already been accepted!");
            }

            friendship.setAccepted(true);
            friendshipRepository.save(friendship);
            return ResponseEntity.ok("Friend request accepted successfully!");
        }

        return ResponseEntity.badRequest().body("Friend request not found!");
    }

    // Get the friend list for a given userId
    public ResponseEntity<List<String>> getFriendList(String userId) {
        // Retrieve all friendships where the user is either the sender or receiver and the friendship is accepted
        List<Friendship> friendships = friendshipRepository.findBySenderIdOrReceiverIdAndAcceptedTrue(userId);

        // Debugging - Check the size of the friendships list
        System.out.println("Number of friendships found: " + friendships.size());
        friendships.forEach(friendship -> {
            System.out.println("Sender: " + friendship.getSenderId() + ", Receiver: " + friendship.getReceiverId() + ", Accepted: " + friendship.isAccepted());
        });

        // Extract friend IDs from the friendships
        List<String> friends = friendships.stream()
                .map(friendship -> friendship.getSenderId().equals(userId) ? friendship.getReceiverId() : friendship.getSenderId())
                .collect(Collectors.toList());

        // Return the list of friends
        return ResponseEntity.ok(friends);
    }



}*/
