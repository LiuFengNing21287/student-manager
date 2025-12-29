package com.example.studentmanager.repository;

import com.example.studentmanager.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByReceiverUsernameOrderBySendTimeAsc(String receiverUsername);

    List<ChatMessage> findBySenderUsernameAndReceiverUsernameOrderBySendTimeAsc(
            String sender, String receiver
    );
}
