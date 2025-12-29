package com.example.studentmanager.controller;

import com.example.studentmanager.entity.ChatMessage;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.repository.ChatMessageRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    /**
     * 聊天页面
     */
    @GetMapping
    public String chatPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<ChatMessage> messages =
                chatMessageRepository.findByReceiverUsernameOrderBySendTimeAsc(user.getUsername());

        model.addAttribute("messages", messages);
        model.addAttribute("username", user.getUsername());
        return "chat";
    }

    /**
     * 发送消息
     */
    @PostMapping("/send")
    public String sendMessage(
            @RequestParam String receiverUsername,
            @RequestParam String content,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("loginUser");

        ChatMessage message = new ChatMessage();
        message.setSenderUsername(user.getUsername());
        message.setReceiverUsername(receiverUsername);
        message.setContent(content);

        chatMessageRepository.save(message);
        return "redirect:/chat";
    }
}
