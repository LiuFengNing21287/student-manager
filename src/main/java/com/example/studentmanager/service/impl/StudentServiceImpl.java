package com.example.studentmanager.service.impl;

import com.example.studentmanager.entity.Course;
import com.example.studentmanager.entity.Score;
import com.example.studentmanager.repository.CourseRepository;
import com.example.studentmanager.repository.ScoreRepository;
import com.example.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Score> getScoresByStudentId(Long studentId) {
        return scoreRepository.findByStudentId(studentId);
    }

    @Override
    public boolean selectCourse(Long studentId, Long courseId) {

        Score exist = scoreRepository
                .findByStudentIdAndCourseId(studentId, courseId);

        if (exist != null) {
            return false; // 已选
        }

        Score score = new Score();
        score.setStudentId(studentId);
        score.setCourseId(courseId);
        score.setScore(null);

        scoreRepository.save(score);
        return true;
    }

    @Override
    public void dropCourse(Long studentId, Long courseId) {
        scoreRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public List<Score> getScores(Long studentId) {
        return scoreRepository.findByStudentId(studentId);
    }

}
