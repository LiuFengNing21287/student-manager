package com.example.studentmanager.controller;

import com.example.studentmanager.entity.Course;
import com.example.studentmanager.entity.Score;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.repository.CourseRepository;
import com.example.studentmanager.repository.ScoreRepository;
import com.example.studentmanager.service.HomeworkService;
import com.example.studentmanager.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private HomeworkService homeworkService;

    // 学生首页
    @GetMapping
    public String index() {
        return "student/index";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", studentService.getAllCourses());
        return "student/courses";
    }



    @GetMapping("/select/{courseId}")
    public String selectCourse(@PathVariable Long courseId,
                               HttpSession session) {

        User student = (User) session.getAttribute("loginUser");
        studentService.selectCourse(student.getId(), courseId);
        return "redirect:/student/courses";
    }


    @GetMapping("/drop/{courseId}")
    public String dropCourse(@PathVariable Long courseId,
                             HttpSession session) {

        User student = (User) session.getAttribute("loginUser");

        scoreRepository.deleteByStudentIdAndCourseId(
                student.getId(), courseId
        );

        return "redirect:/student/courses";
    }

    @GetMapping("/scores")
    public String scores(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            return "redirect:/login";
        }

        List<Score> scores =
                studentService.getScoresByStudentId(user.getId());

        model.addAttribute("scores", scores);
        return "student/scores";
    }

    //作业
    @GetMapping("/homework")
    public String viewHomework(Model model) {
        model.addAttribute("homeworks", homeworkService.findAll());
        return "student/homework";
    }

}
