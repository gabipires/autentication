package com.javamaster.springsecurityjwt.repository;

import com.javamaster.springsecurityjwt.entity.TasksEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksEntityRepository extends JpaRepository<TasksEntity, Integer> {

    
}
