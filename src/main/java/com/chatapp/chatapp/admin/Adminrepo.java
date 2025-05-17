package com.chatapp.chatapp.admin;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Adminrepo extends JpaRepository<Adminmodel,Integer>{

    Optional<Adminmodel> findByUsernameAndPassword(String username, String password);


    Adminmodel findByUsername(String username);
}


