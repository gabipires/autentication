package com.javamaster.springsecurityjwt.repository;

import com.javamaster.springsecurityjwt.entity.UsersEntity;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersEntityRepository extends PagingAndSortingRepository<UsersEntity, Integer> {
    UsersEntity findByUsername(String username);
}
