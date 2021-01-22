package com.javamaster.springsecurityjwt.mapper;

import com.javamaster.springsecurityjwt.dto.request.TasksCreateRequest;
import com.javamaster.springsecurityjwt.dto.response.TasksResponse;
import com.javamaster.springsecurityjwt.entity.TasksEntity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TasksMapper {
    private final ModelMapper tasksMapper;

    @Autowired
    public TasksMapper(ModelMapper tasksMapper){
        this.tasksMapper = tasksMapper;
    }

    public TasksResponse toDto(TasksEntity input){ //do BD para JSON
        TasksResponse response = tasksMapper.map(input, TasksResponse.class);
        response.setUser_id(input.getUserEntity().getId());
        response.setUsername(input.getUserEntity().getUsername());
        return response;
    }

    public TasksEntity fromDto(TasksCreateRequest input){ //de JSON para BD
        return tasksMapper.map(input, TasksEntity.class);
    }





    
}
