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
@RequestMapping("")
public class TasksController {
    private final TasksService tasksService;
    private final TasksMapper tasksMapper;

    @Autowired
    public TasksController(TasksService tasksService, TasksMapper tasksMapper){
        this.tasksService = tasksService;
        this.tasksMapper = tasksMapper;
    }

    @GetMapping(value="/")
    public String home(){
        return 
        "========  Estudos pré projeto - Autorização e Autenticação de usuários =======";
    }  


    //List all tasks
    @GetMapping(value="/task/list")
    public ResponseEntity<List<TasksResponse>> list(){
        return ResponseEntity.ok(tasksService.listTasks()
                .stream().map(x -> tasksMapper.toDto(x))
                .collect(Collectors.toList()));
    }


    //List task por id
    @GetMapping(value="/task/list/{id}")
    public ResponseEntity<TasksResponse> listByUser(@PathVariable Integer id){
        return ResponseEntity.ok(tasksMapper.toDto(tasksService.listTasksByUser(id)));
    }  


    // Delete task
    @GetMapping(value="/admin/delete")
    public String delete(){
        return " ======  Endpoint DELETE task (TODO)  =======";
    }

    //Post task
    @GetMapping(value="/admin/post")
    public String post(){
        return " ======  Endpoint POST task (TODO)  =======";
    }

    //Put task
    @GetMapping(value="/admin/put")
    public String put(){
        return " ======  Endpoint PUT task (TODO)  =======";
    }
}
