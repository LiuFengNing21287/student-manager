package com.example.studentmanager.service;

import com.example.studentmanager.entity.Course;
import com.example.studentmanager.entity.Score;

import java.util.List;

public interface TeacherService {

    List<Course> getMyCourses(Long teacherId);

    boolean inputScore(Score score);

    String saveScore(Long studentId, Long courseId, Integer score);
}

