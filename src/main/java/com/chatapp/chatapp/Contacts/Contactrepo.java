package com.chatapp.chatapp.Contacts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Contactrepo extends JpaRepository<Contactmodel, Long> {


    List<Contactmodel> findByUserId(Integer userId);
}
