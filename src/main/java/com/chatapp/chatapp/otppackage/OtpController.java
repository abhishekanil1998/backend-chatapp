package com.chatapp.chatapp.otppackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping(path = "/api/chatapp")
public class OtpController {
    @Autowired
    private OtpService otpService;

    @PostMapping("/otp")
    public ResponseEntity<?> registerUser(@RequestBody OtpModel otpModel) {
        return otpService.registerUser(otpModel);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestParam String session, @RequestParam String otp) {
        return otpService.verifyOtp(session,otp);
    }
}
