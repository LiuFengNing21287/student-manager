package com.example.studentmanager.controller;

import com.example.studentmanager.entity.Course;
import com.example.studentmanager.entity.Homework;
import com.example.studentmanager.entity.Score;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.repository.CourseRepository;
import com.example.studentmanager.repository.ScoreRepository;
import com.example.studentmanager.repository.UserRepository;
import com.example.studentmanager.service.HomeworkService;
import com.example.studentmanager.service.TeacherService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private HomeworkService homeworkService;


    // 教师首页
    @GetMapping
    public String index() {
        return "teacher/index";
    }

    // 我的课程
    @GetMapping("/courses")
    public String courses(HttpSession session, Model model) {

        User teacher = (User) session.getAttribute("loginUser");
        List<Course> courses = courseRepository.findByTeacherId(teacher.getId());

        model.addAttribute("courses", courses);
        return "teacher/courses";
    }

    // 成绩录入页面
    @GetMapping("/score")
    public String scorePage() {
        return "teacher/score-input";
    }

    @PostMapping("/score/save")
    public String saveScore(Long studentId,
                            Long courseId,
                            Integer score,
                            Model model) {

        String result = teacherService
                .saveScore(studentId, courseId, score);

        if (!"success".equals(result)) {
            model.addAttribute("msg", result);
            return "teacher/score-input";
        }

        return "redirect:/teacher";
    }

    @GetMapping("/score/input")
    public String scoreInput() {
        return "teacher/score-input"; // 返回视图名
    }

    // 学生管理
    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("scores", scoreRepository.findAll());
        return "teacher/students";
    }
    //作业
    @GetMapping("/homework")
    public String homeworkList(Model model) {
        model.addAttribute("homeworks", homeworkService.findAll());
        return "teacher/homework";
    }

    @GetMapping("/homework/add")
    public String addHomeworkPage() {
        return "teacher/homework-add";
    }

    @PostMapping("/homework/add")
    public String addHomework(Homework homework) {
        homeworkService.save(homework);
        return "redirect:/teacher/homework";
    }

    @GetMapping("/homework/delete")
    public String deleteHomework(Integer id) {
        homeworkService.deleteById(id);
        return "redirect:/teacher/homework";
    }
    @GetMapping("/homework/edit")
    public String editHomeworkPage(Integer id, Model model) {
        Homework homework = homeworkService.findById(id);
        model.addAttribute("homework", homework);
        return "teacher/homework-edit";
    }
    @PostMapping("/homework/edit")
    public String editHomework(Homework homework) {
        homeworkService.save(homework);
        return "redirect:/teacher/homework";
    }

}
