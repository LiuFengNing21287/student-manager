package com.example.studentmanager.controller;

import com.example.studentmanager.entity.User;
import com.example.studentmanager.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // 1. 打开登录页
    @GetMapping({"/", "/login"})
    public String loginPage() {
        return "login";
    }

    // 2. 处理登录
    @PostMapping("/login")
    public String doLogin(String username,
                          String password,
                          HttpSession session,
                          Model model) {

        User user = userRepository.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }

        // 登录成功，存 session
        session.setAttribute("loginUser", user);

        // 按角色跳转
        if ("STUDENT".equals(user.getRole())) {
            return "redirect:/student";
        } else if ("TEACHER".equals(user.getRole())) {
            return "redirect:/teacher";
        } else if ("ADMIN".equals(user.getRole())) {
            return "redirect:/admin";
        }

        return "login";
    }

    // 3. 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
