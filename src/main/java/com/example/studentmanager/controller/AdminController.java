package com.example.studentmanager.controller;

import com.example.studentmanager.entity.Course;
import com.example.studentmanager.repository.CourseRepository;
import com.example.studentmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    // 管理员首页
    @GetMapping
    public String index() {
        return "admin/index";
    }

    // 用户管理
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    // 课程管理页面
    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "admin/courses";
    }

    // 新增课程
    @PostMapping("/course/save")
    public String saveCourse(Course course) {
        courseRepository.save(course);
        return "redirect:/admin/courses";
    }

    // 系统配置
    @GetMapping("/config")
    public String config() {
        return "admin/config";
    }
}
