package com.example.demo.repository;

import com.example.demo.model.Friendship;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends MongoRepository<Friendship, String> {
    Optional<Friendship> findBySenderIdAndReceiverId(String senderId, String receiverId);
    List<Friendship> findByReceiverIdAndAcceptedFalse(String receiverId);
    List<Friendship> findBySenderIdOrReceiverIdAndAcceptedTrue(String senderId, String receiverId);

}

/*public interface FriendshipRepository extends MongoRepository<Friendship, String> {

    // Query to find a friendship in a specific direction
    Optional<Friendship> findBySenderIdAndReceiverId(String senderId, String receiverId);
    // Query to check if users are friends (in either direction) and friendship is accepted
    @Query("{$or: [ { 'senderId': ?0, 'receiverId': ?1, 'accepted': true }, { 'senderId': ?1, 'receiverId': ?0, 'accepted': true } ]}")
    List<Friendship> findAcceptedFriendships(String userId1, String userId2);

    // Query to find all accepted friendships for a user (user is either sender or receiver)
    @Query("{$or: [ { 'senderId': ?0 }, { 'receiverId': ?0 } ], 'accepted': true}")
    List<Friendship> findBySenderIdOrReceiverIdAndAcceptedTrue(String userId);
    @Query("{$and: [ { 'receiverId': ?0 }, { 'accepted': false } ]}")
    List<Friendship> findPendingRequests(String receiverId);

}*/

/* BU package com.example.demo.repository;

import com.example.demo.model.Friendship;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends MongoRepository<Friendship, String> {

    // Query to find a friendship in a specific direction
    Optional<Friendship> findBySenderIdAndReceiverId(String senderId, String receiverId);

    // Query to check if users are friends (in either direction) and friendship is accepted
    @Query("{$or: [ { 'senderId': ?0, 'receiverId': ?1, 'accepted': true }, { 'senderId': ?1, 'receiverId': ?0, 'accepted': true } ]}")
    List<Friendship> findAcceptedFriendships(String userId1, String userId2);

    // Query to find all accepted friendships for a user (user is either sender or receiver)
    @Query("{$or: [ { 'senderId': ?0 }, { 'receiverId': ?0 } ], 'accepted': true}")
    List<Friendship> findBySenderIdOrReceiverIdAndAcceptedTrue(String userId);
}*/


/*package com.example.demo.repository;

import com.example.demo.model.Friendship;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FriendshipRepository extends MongoRepository<Friendship, String> {
    Optional<Friendship> findBySenderIdAndReceiverId(String senderId, String receiverId);

    List<Friendship> findBySenderIdOrReceiverIdAndAcceptedTrue(String senderId, String receiverId);
}
*/