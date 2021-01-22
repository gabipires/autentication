package com.javamaster.springsecurityjwt.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "USERS")
@Data

public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, length = 40)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;
    
    @Column(name = "admin", nullable = false)
    private boolean admin;

}
