package com.chatapp.chatapp.Friend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepo extends JpaRepository<FriendModel,Integer> {


    Optional<FriendModel> findBySenderIdAndReceiverId(Integer senderId, Integer receiverId);
}
