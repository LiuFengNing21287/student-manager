package com.example.studentmanager.service;

import com.example.studentmanager.entity.Homework;
import java.util.List;

public interface HomeworkService {

    List<Homework> findAll();

    Homework findById(Integer id);

    void save(Homework homework);

    void deleteById(Integer id);
}
