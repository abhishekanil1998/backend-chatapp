package com.chatapp.chatapp.admin;

import com.chatapp.chatapp.Dto.AdminLogindto;
import com.chatapp.chatapp.Dto.UserDto;
import com.chatapp.chatapp.user.Usermodel;
import com.chatapp.chatapp.user.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/Admindetails")
@CrossOrigin(origins = "*")
public class Admincontroller {
    @Autowired
    private Adminservice adminservice;

    @PostMapping(path = "/registration")
    public ResponseEntity<?> Adminregistration(@RequestBody Adminmodel adminmodel) {

        try {
            return adminservice.registration(adminmodel);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>("SWW", HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @PostMapping(path = "/login")
    public ResponseEntity<?>Adminlogin(@RequestBody AdminLogindto adminLogindto){
        try {
            return adminservice.login(adminLogindto);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>("SWW", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(path="/update-password")
    public String updatePassword(@RequestParam String username, @RequestParam String newPassword) {
        return adminservice.updatePassword(username, newPassword);
    }

    @GetMapping(path = "/viewUsers")
    public ResponseEntity<List<Usermodel>> getAllUsers() {
        return adminservice.getAllUsers();

    }


}





