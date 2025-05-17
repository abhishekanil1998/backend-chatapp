package com.chatapp.chatapp.message;

import com.chatapp.chatapp.user.Usermodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class Messageservice {

    @Autowired
    private Messagerepo messagerepo;

    private static final String UPLOAD_DIR = "uploads/";

    public ResponseEntity<?> message(Integer senderId, Integer receiverId, Messagemodel messagemodel) {
        Messagemodel messagemodel1 = new Messagemodel();
        messagemodel1.setSenderId(senderId);
        messagemodel1.setReceiverId(receiverId);
        messagemodel1.setContent(messagemodel.getContent());
        messagemodel1.setTime(LocalDateTime.now());
        messagerepo.save(messagemodel1);
        return new ResponseEntity<>(messagemodel1, HttpStatus.OK);
    }

    public ResponseEntity<?> editMessage(Integer messageId, String content, Integer senderId) {
        Optional<Messagemodel> optionalMessage = messagerepo.findByMessageIdAndSenderId(messageId, senderId);
        if (optionalMessage.isPresent()) {
            Messagemodel message = optionalMessage.get();
            if (Duration.between(message.getTime(), LocalDateTime.now()).toMinutes() <= 15) {
                message.setContent(content);
                messagerepo.save(message);
                return new ResponseEntity<>("Message updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Message editing time expired", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>("Message not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteMessage(Integer messageId) {
        Optional<Messagemodel> optionalMessage = messagerepo.findById(messageId);
        if (optionalMessage.isPresent()) {
            Messagemodel message = optionalMessage.get();
            if (Duration.between(message.getTime(), LocalDateTime.now()).toMinutes() <= 15) {
                messagerepo.delete(message);
                return new ResponseEntity<>("Message deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Message deletion time expired", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>("Message not found", HttpStatus.NOT_FOUND);
    }

    private String saveFile(MultipartFile file) throws IOException {
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.write(filePath, file.getBytes());

        return filePath.toString();
    }

    public ResponseEntity<?> Imageupload(Integer senderId, Integer receiverId, MultipartFile image) {
        try {
            String filePath = saveFile(image);
            Messagemodel message = new Messagemodel();
            message.setSenderId(senderId);
            message.setReceiverId(receiverId);
            message.setContent("Image uploaded");
            message.setFilePath(filePath);
            message.setTime(LocalDateTime.now());
            messagerepo.save(message);

            return new ResponseEntity<>("Image uploaded successfully: " + filePath, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> Document(Integer senderId, Integer receiverId, MultipartFile document) {
        try {
            String filePath = saveFile(document);
            Messagemodel message = new Messagemodel();
            message.setSenderId(senderId);
            message.setReceiverId(receiverId);
            message.setContent("Document uploaded");
            message.setFilePath(filePath);
            message.setTime(LocalDateTime.now());
            messagerepo.save(message);

            return new ResponseEntity<>("Document uploaded successfully: " + filePath, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> Files(Integer senderId, Integer receiverId, MultipartFile file) {
        try {
            String filePath = saveFile(file);
            Messagemodel message = new Messagemodel();
            message.setSenderId(senderId);
            message.setReceiverId(receiverId);
            message.setContent("File uploaded");
            message.setFilePath(filePath);
            message.setTime(LocalDateTime.now());
            messagerepo.save(message);

            return new ResponseEntity<>("File uploaded successfully: " + filePath, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> Audio(Integer senderId, Integer receiverId, MultipartFile audio) {
        try {
            String filePath = saveFile(audio);
            Messagemodel message = new Messagemodel();
            message.setSenderId(senderId);
            message.setReceiverId(receiverId);
            message.setContent("Audio uploaded");
            message.setFilePath(filePath);
            message.setTime(LocalDateTime.now());
            messagerepo.save(message);

            return new ResponseEntity<>("Audio uploaded successfully: " + filePath, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> viewmessage(Integer senderId, Integer receiverId) {
        Optional<Messagemodel> messagemodelOptional = messagerepo.findByMessageIdAndSenderId(senderId,receiverId);

        if (messagemodelOptional.isPresent()) {
            Messagemodel messagemodel = messagemodelOptional.get();
            return new ResponseEntity<>(messagemodel,HttpStatus.OK);
        }else {
            return new ResponseEntity<>( "Messages not found",HttpStatus.NOT_FOUND);
        }
    }
}
