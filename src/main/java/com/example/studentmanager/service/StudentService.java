package com.example.studentmanager.service;

import com.example.studentmanager.entity.Course;
import com.example.studentmanager.entity.Score;

import java.util.List;

public interface StudentService {

    List<Course> getAllCourses();

    List<Score> getScoresByStudentId(Long studentId);

    boolean selectCourse(Long studentId, Long courseId);

    void dropCourse(Long studentId, Long courseId);

    List<Score> getScores(Long studentId);
}
