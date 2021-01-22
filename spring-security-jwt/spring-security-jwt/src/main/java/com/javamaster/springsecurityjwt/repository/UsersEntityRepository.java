package com.javamaster.springsecurityjwt.repository;

import com.javamaster.springsecurityjwt.entity.UsersEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersEntityRepository extends JpaRepository<UsersEntity, Integer> {
    
    
}
