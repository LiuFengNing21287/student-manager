package com.example.studentmanager.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_username")
    private String senderUsername;

    @Column(name = "receiver_username")
    private String receiverUsername;

    @Column(name = "content")
    private String content;

    @Column(name = "send_time")
    private LocalDateTime sendTime;

    public ChatMessage() {}

    @PrePersist
    public void prePersist() {
        this.sendTime = LocalDateTime.now();
    }


    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


