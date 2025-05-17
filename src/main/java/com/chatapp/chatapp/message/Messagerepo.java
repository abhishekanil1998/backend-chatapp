package com.chatapp.chatapp.message;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Messagerepo extends JpaRepository<Messagemodel,Integer> {


    Optional<Messagemodel> findByMessageIdAndSenderId(Integer messageId, Integer senderId);


    void deleteBySenderId(Integer userId);

}
