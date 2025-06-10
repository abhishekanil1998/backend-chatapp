package com.chatapp.chatapp.user;

import com.chatapp.chatapp.Dto.AdminLogindto;
import com.chatapp.chatapp.Dto.UserDto;
import com.chatapp.chatapp.Dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/Userdetails")
@CrossOrigin(origins = "*")
public class Usercontroller {
    @Autowired
    private Userservice userservice;

    @PostMapping(path = "/signup")
    public ResponseEntity<?> userregistration(@RequestBody Usermodel usermodel, @RequestParam String sessionId) {
        try {
            return userservice.userregistration(usermodel, sessionId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(path = "/login")
    public ResponseEntity<?>Userlogin(@RequestBody UserLoginDto userLoginDto){
        try {
            return userservice.login(userLoginDto);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>("SWW", HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @PutMapping (path = "/addusername")
//    public ResponseEntity<?>addusername(@RequestParam String Username,@RequestParam Integer UserID)   {
//        try {
//            return userservice.addusername(Username,UserID);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @GetMapping(path = "/viewProfile")
    public ResponseEntity<?> viewProfile(@RequestParam Integer userId) {
        try {
            return userservice.viewProfile(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("S W W", HttpStatus.INTERNAL_SERVER_ERROR);
    }




    @DeleteMapping("/Deleteuser")
    public ResponseEntity<?> deleteUser(@RequestParam Integer userId) {
        try {
            return userservice.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("S W W", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //list all users
    @GetMapping("/getuser")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(required = false) String name) {
        List<UserDto> users = userservice.getUsersByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



    //search user
    @GetMapping(path = "/SearchUser")
    public ResponseEntity<?> searchUserController(@RequestParam String name) {
        try {
            return userservice.searchUser(name);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Search failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

