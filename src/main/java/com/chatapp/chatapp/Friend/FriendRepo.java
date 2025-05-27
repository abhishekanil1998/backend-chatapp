package com.chatapp.chatapp.Friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRepo extends JpaRepository<FriendModel,Integer> {


    Optional<FriendModel> findBySenderIdAndReceiverId(Integer senderId, Integer receiverId);

    @Query("SELECT f FROM FriendModel f WHERE (f.senderId = :userId OR f.receiverId = :userId) AND f.friendStatus = 1")
    List<FriendModel> findApprovedFriendsByUserId(@Param("userId") Integer userId);

}
