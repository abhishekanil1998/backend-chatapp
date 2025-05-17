package com.chatapp.chatapp.admin;

import com.chatapp.chatapp.Dto.AdminLogindto;
import com.chatapp.chatapp.Dto.UserDto;
import com.chatapp.chatapp.user.Usermodel;
import com.chatapp.chatapp.user.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class Adminservice {
    @Autowired
    private Adminrepo adminrepo;
    @Autowired
    private Userrepo userrepo;

    public ResponseEntity<?> registration(Adminmodel adminmodel) {
        Adminmodel adminmodel1 =new Adminmodel();
        adminmodel1.setUsername(adminmodel.getUsername());
        adminmodel1.setPassword(adminmodel.getPassword());

        adminrepo.save(adminmodel1);
        return new ResponseEntity<>(adminmodel1, HttpStatus.OK);


    }

    public ResponseEntity<?> login(AdminLogindto adminLogindto) {

        Optional<Adminmodel> adminmodelOptional =adminrepo.findByUsernameAndPassword(adminLogindto.getUsername(),adminLogindto.getPassword());
        if (adminmodelOptional.isPresent()){
            return new ResponseEntity<>("Loginsuccessful",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Loginfailed",HttpStatus.NOT_FOUND);
        }
    }

    public String updatePassword(String username, String newPassword) {
        Adminmodel adminmodel= adminrepo.findByUsername(username);
        if (adminmodel != null) {
            adminmodel.setPassword(newPassword);
            adminrepo.save(adminmodel);
            return "Password updated successfully";
        }
        return "Admin not found";
    }

    public ResponseEntity<List<Usermodel>> getAllUsers() {
        List<Usermodel> usermodels = userrepo.findAll();

        if (usermodels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usermodels);
    }



}

