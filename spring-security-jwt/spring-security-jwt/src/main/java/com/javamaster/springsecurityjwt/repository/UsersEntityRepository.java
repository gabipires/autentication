package com.javamaster.springsecurityjwt.repository;


import java.util.Optional;

import com.javamaster.springsecurityjwt.entity.UsersEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersEntityRepository extends JpaRepository<UsersEntity, Integer> {
    Optional<UsersEntity> findByUsername(String username);
}
