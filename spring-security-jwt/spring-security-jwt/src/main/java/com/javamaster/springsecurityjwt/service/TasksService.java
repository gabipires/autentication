package com.javamaster.springsecurityjwt.service;

import java.util.List;
import java.util.Optional;

import com.javamaster.springsecurityjwt.entity.TasksEntity;
import com.javamaster.springsecurityjwt.repository.TasksEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksService {

    private final TasksEntityRepository tasksEntityRepository;

    @Autowired
    public TasksService(TasksEntityRepository tasksEntityRepository){
        this.tasksEntityRepository = tasksEntityRepository;
    }


    public List<TasksEntity> listTasks(){
        return tasksEntityRepository.findAll();
    }
    
    public TasksEntity listTasksByUser(Integer id){
        Optional<TasksEntity> tasks = tasksEntityRepository.findById(id);
        return tasks.orElseThrow();
    }
}
