package com.chatapp.chatapp.otppackage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepo extends JpaRepository<OtpModel,Integer> {
    Optional<OtpModel> findByPhoneNumber(String phoneNumber);
}
