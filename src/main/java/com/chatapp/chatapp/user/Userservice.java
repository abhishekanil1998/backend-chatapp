package com.chatapp.chatapp.user;

import com.chatapp.chatapp.Dto.UserDto;
import com.chatapp.chatapp.admin.Adminmodel;
import com.chatapp.chatapp.message.Messagerepo;
import com.chatapp.chatapp.otppackage.OtpModel;
import com.chatapp.chatapp.otppackage.OtpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class Userservice {
    @Autowired
    private Userrepo userrepo;
    private Messagerepo messagerepo;
    @Autowired
    private OtpRepo otpRepo;

    public ResponseEntity<?> registration(Usermodel usermodel,String sessionId) {
//        Usermodel usermodel1 = new Usermodel();
//        usermodel1.setName(usermodel.getName());
//        usermodel1.setMobile(usermodel.getMobile());
//        userrepo.save(usermodel1);
//        return new ResponseEntity<>(usermodel1, HttpStatus.OK);

        OtpModel otpModel = otpRepo.findBySessionId((sessionId));

        if (otpModel == null) {
            return new ResponseEntity<>("Phone number not verified via OTP", HttpStatus.BAD_REQUEST);
        }

        Usermodel usermodel1 = new Usermodel();
        usermodel1.setName(usermodel.getName());
        usermodel1.setMobile(otpModel.getPhoneNumber());
        usermodel1.setUserId(otpModel.getUserId());
        usermodel1.setPassword(usermodel.getPassword());



        // Set additional fields if needed
        // usermodel1.setEmail(usermodel.getEmail());
        // usermodel1.setPassword(usermodel.getPassword());

        userrepo.save(usermodel1);
        return new ResponseEntity<>(usermodel1, HttpStatus.OK);
    }


    public ResponseEntity<?> deleteUser(Integer userId) {
        Optional<Usermodel> userOptional = userrepo.findById(userId);
        if (userOptional.isPresent()) {
            Usermodel user = userOptional.get();

            // Delete the user account
            userrepo.delete(user);

            return new ResponseEntity<>("User account deleted successfully.",HttpStatus.OK);
        }else {
            return new ResponseEntity<>( "User not found",HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> viewProfile(Integer userId) {
        Optional<Usermodel> userOptional = userrepo.findById(userId);

        if (userOptional.isPresent()) {
            Usermodel user = userOptional.get();
            return new ResponseEntity<>(user,HttpStatus.OK);
        }else {
            return new ResponseEntity<>( "User not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addusername(String username, Integer userID) {
        Optional<Usermodel>usermodelOptional =userrepo.findById(userID);
        if (usermodelOptional.isPresent()){
            Usermodel user = usermodelOptional.get();
        }
        return null;
    }

    public ResponseEntity<List<UserDto>> getallusers() {
        List<UserDto> dto =new ArrayList<>();
        List<Usermodel>usermodels =userrepo.findAll();
        if (!usermodels.isEmpty()){
            for (Usermodel usermodel : usermodels){
                UserDto userDto1 =new UserDto();
                userDto1.setName(usermodel.getName());
                dto.add(userDto1);
            }
        }
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    public List<UserDto> getUsersByName(String name) {
        List<Usermodel> usermodels;

        if (name != null && !name.isEmpty()) {
            usermodels = userrepo.findByNameContainingIgnoreCase(name);
        } else {
            usermodels = userrepo.findAll();
        }

        return usermodels.stream().map(usermodel -> {
            UserDto dto = new UserDto();
            dto.setName(usermodel.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    public ResponseEntity<?> searchUser(String name) {
        List<Usermodel> usermodels = userrepo.findByNameContainingIgnoreCase(name);
        return new ResponseEntity<>(usermodels, HttpStatus.OK);
    }

    public ResponseEntity<?> login(String mobile, String password) {
        Optional<Usermodel> usermodelOptional =userrepo.findByMobileAndPassword(mobile,password);
        if (usermodelOptional.isPresent()){
            return new ResponseEntity<>("Loginsuccessful",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Loginfailed",HttpStatus.NOT_FOUND);
        }

    }


//    public ResponseEntity<String> deleteUser(Integer userId) {
//        Optional<Usermodel> userOptional = userrepo.findById(userId);
//        if (userOptional.isPresent()) {
//            Usermodel user = userOptional.get();
//
//            // Delete the user account
//            userrepo.delete(user);
//
//            return ResponseEntity.ok("User account deleted successfully.");
//        }
//
//        return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//
//    }
}






