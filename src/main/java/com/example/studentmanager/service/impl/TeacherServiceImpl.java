package com.example.studentmanager.service.impl;

import com.example.studentmanager.entity.Course;
import com.example.studentmanager.entity.Score;
import com.example.studentmanager.repository.CourseRepository;
import com.example.studentmanager.repository.ScoreRepository;
import com.example.studentmanager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public boolean inputScore(Score score) {

        // 查是否已有选课/成绩记录
        Score exist = scoreRepository.findByStudentIdAndCourseId(
                score.getStudentId(),
                score.getCourseId()
        );

        // 未选课
        if (exist == null) {
            return false;
        }

        // 已录成绩，禁止覆盖
        if (exist.getScore() != null) {
            return false;
        }

        // 正常录入
        exist.setScore(score.getScore());
        scoreRepository.save(exist);

        return true;
    }

    @Override
    public List<Course> getMyCourses(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }

    @Override
    public String saveScore(Long studentId, Long courseId, Integer scoreValue) {

        Score exist = scoreRepository
                .findByStudentIdAndCourseId(studentId, courseId);

        if (exist == null) {
            return "该学生未选此课程";
        }

        if (exist.getScore() != null) {
            return "成绩已存在，不能修改";
        }

        exist.setScore(scoreValue);
        scoreRepository.save(exist);

        return "success";
    }
}
