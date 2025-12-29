package com.example.studentmanager.service.impl;

import com.example.studentmanager.entity.Homework;
import com.example.studentmanager.repository.HomeworkRepository;
import com.example.studentmanager.service.HomeworkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public List<Homework> findAll() {
        return homeworkRepository.findAll();
    }

    @Override
    public Homework findById(Integer id) {
        return homeworkRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Homework homework) {
        homeworkRepository.save(homework);
    }

    @Override
    public void deleteById(Integer id) {
        homeworkRepository.deleteById(id);
    }
}
