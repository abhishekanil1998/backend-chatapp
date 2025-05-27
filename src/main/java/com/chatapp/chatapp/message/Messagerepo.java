package com.chatapp.chatapp.message;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Messagerepo extends JpaRepository<Messagemodel,Integer> {


//    Optional<Messagemodel> findByMessageIdAndSenderId(Integer messageId, Integer senderId);


    void deleteBySenderId(Integer userId);

//    Optional<Messagemodel> findBySenderIdndSenderId(Integer senderId, Integer receiverId);


    Optional<Messagemodel> findByMessageIdAndSenderId(Integer messageId, Integer senderId);


    @Query("SELECT m FROM Messagemodel m WHERE " +
            "(m.senderId = :senderId AND m.receiverId = :receiverId) OR " +
            "(m.senderId = :receiverId AND m.receiverId = :senderId) " +
            "ORDER BY m.time ASC")
    List<Messagemodel> findConversation(@Param("senderId") Integer senderId,
                                        @Param("receiverId") Integer receiverId);

}
