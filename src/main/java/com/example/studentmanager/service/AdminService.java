package com.example.studentmanager.service;

import com.example.studentmanager.entity.Course;
import com.example.studentmanager.entity.User;

import java.util.List;

public interface AdminService {

    List<User> getAllUsers();

    List<Course> getAllCourses();

    void saveCourse(Course course);
}
