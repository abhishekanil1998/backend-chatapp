package com.chatapp.chatapp.message;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "messages")
public class Messagemodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer messageId;

    @Column(name = "sender"  )
    private Integer senderId;

    @Column(name = "receiver")
    private Integer receiverId;

    @Column(name = "content")
    private String content;

    private String filePath; // File path for uploaded files


    @Column(name = "time")
    private LocalDateTime time = LocalDateTime.now();




    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

//    public byte[] getImage() {
//        return Image;
//    }
//
//    public void setImage(byte[] image) {
//        Image = image;
//    }


}
