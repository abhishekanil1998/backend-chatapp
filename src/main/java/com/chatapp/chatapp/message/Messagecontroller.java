package com.chatapp.chatapp.message;

import com.chatapp.chatapp.Dto.MessageDto;
import com.chatapp.chatapp.Friend.FriendModel;
import com.chatapp.chatapp.user.Usermodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping(path = "/api/message")
@CrossOrigin(origins = "*")
public class Messagecontroller {

    @Autowired
    private Messageservice messageservice;

    @PostMapping(path = "/send")
    public ResponseEntity<?> message(@RequestBody MessageDto messageDto) {
        try {
            return messageservice.message(messageDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/viewmessages")
    public ResponseEntity<?> viewmessage(@RequestParam Integer senderId,
                                         @RequestParam Integer receiverId) {
        try {
            return messageservice.viewmessage(senderId, receiverId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PutMapping(path = "/edit")
    public ResponseEntity<?> editMessage(@RequestParam Integer messageId,
                                         @RequestParam String content,
                                         @RequestParam Integer senderId) {
        try {
            return messageservice.editMessage(messageId, content, senderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> deleteMessage(@RequestParam Integer messageId) {
        try {
            return messageservice.deleteMessage(messageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Upload Image
    @PostMapping("/upload/image")
    public ResponseEntity<?> uploadImage(@RequestParam Integer senderId,
                                         @RequestParam Integer receiverId,
                                         @RequestParam("image") MultipartFile image) {
        try {
            return messageservice.Imageupload(senderId, receiverId, image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Image upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Upload Document
    @PostMapping("/upload/document")
    public ResponseEntity<?> uploadDocument(@RequestParam Integer senderId,
                                            @RequestParam Integer receiverId,
                                            @RequestParam("document") MultipartFile document) {
        try {
            return messageservice.Document(senderId, receiverId, document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Document upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Upload File
    @PostMapping("/upload/file")
    public ResponseEntity<?> uploadFile(@RequestParam Integer senderId,
                                        @RequestParam Integer receiverId,
                                        @RequestParam("file") MultipartFile file) {
        try {
            return messageservice.Files(senderId, receiverId, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Upload Audio
    @PostMapping("/upload/audio")
    public ResponseEntity<?> uploadAudio(@RequestParam Integer senderId,
                                         @RequestParam Integer receiverId,
                                         @RequestParam("audio") MultipartFile audio) {

        try {
            return messageservice.Audio(senderId, receiverId, audio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Audio upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getfriends/{userId}")
    public ResponseEntity<?> getFriendUserNames(@PathVariable Integer userId) {
        List<Usermodel> friendUsers = messageservice.getFriendUsers(userId);
        if (friendUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No friends found");
        }
        return ResponseEntity.ok(friendUsers);
    }

}
