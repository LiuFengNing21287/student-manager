package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByStudentId(Long studentId);

    Score findByStudentIdAndCourseId(Long studentId, Long courseId);

    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);

}

