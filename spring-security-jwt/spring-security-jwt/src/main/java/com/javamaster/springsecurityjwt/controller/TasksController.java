package com.javamaster.springsecurityjwt.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.javamaster.springsecurityjwt.dto.response.TasksResponse;
import com.javamaster.springsecurityjwt.mapper.TasksMapper;
import com.javamaster.springsecurityjwt.service.TasksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class TasksController {
    private final TasksService tasksService;
    private final TasksMapper tasksMapper;

    @Autowired
    public TasksController(TasksService tasksService, TasksMapper tasksMapper){
        this.tasksService = tasksService;
        this.tasksMapper = tasksMapper;
    }

    @GetMapping(value="tasks")
    public String teste(){
        return " ======  Teste Autentication  =======";
        
    }   

    @GetMapping(value="admin/delete/tasks")
    public String delete(){
        return " ======  Tela de DELETE  =======";
    }

    @GetMapping(value="admin/post/tasks")
    public String post(){
        return " ======  Tela de POST  =======";
    }

    @GetMapping(value="admin/put/tasks")
    public String put(){
        return " ======  Tela de PUT  =======";
    }

    @GetMapping(value="tasks/list")
    public ResponseEntity<List<TasksResponse>> list(){
        return ResponseEntity.ok(tasksService.listTasks()
                .stream().map(x -> tasksMapper.toDto(x))
                .collect(Collectors.toList()));
    }


    @GetMapping(value="tasks/list/{id}")
    public ResponseEntity<TasksResponse> listByUser(@PathVariable Integer id){
        return ResponseEntity.ok(tasksMapper.toDto(tasksService.listTasksByUser(id)));
    }    
}
