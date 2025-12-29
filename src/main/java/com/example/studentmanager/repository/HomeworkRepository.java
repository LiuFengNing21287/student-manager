package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
}
