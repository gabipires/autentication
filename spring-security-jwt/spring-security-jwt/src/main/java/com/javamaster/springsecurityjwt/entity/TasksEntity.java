package com.javamaster.springsecurityjwt.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TASKS")
@Data
public class TasksEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(name="user_id", nullable = false)
    @JoinColumn(name="user_id", nullable = false)
    private UsersEntity userEntity;

    @Column(name="name", nullable = false, length = 40)
    private String name;

    @Column(name="date", nullable = false)
    private LocalDateTime date;
 
  


}
