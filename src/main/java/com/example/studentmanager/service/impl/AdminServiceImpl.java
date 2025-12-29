package com.example.studentmanager.service.impl;

import com.example.studentmanager.entity.Course;
import com.example.studentmanager.entity.Score;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.repository.CourseRepository;
import com.example.studentmanager.repository.ScoreRepository;
import com.example.studentmanager.repository.UserRepository;
import com.example.studentmanager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ScoreRepository scoreRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }
}
