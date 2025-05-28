package com.chatapp.chatapp.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Userrepo extends JpaRepository<Usermodel,Integer> {

    Optional<Usermodel> findByMobile(String mobile);

    List<Usermodel> findByNameContainingIgnoreCase(String name);

    List<Usermodel> findByUserIdIn(List<Integer> userIds);

    Optional<Usermodel> findByMobileAndPassword(String mobile, String password);

    Optional<Usermodel> findByUserId(Integer userId);

}
