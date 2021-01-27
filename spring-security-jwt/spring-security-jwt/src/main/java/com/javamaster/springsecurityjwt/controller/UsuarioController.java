package com.javamaster.springsecurityjwt.controller;


import com.javamaster.springsecurityjwt.dto.request.Credentials;
import com.javamaster.springsecurityjwt.dto.response.TokenDTO;
import com.javamaster.springsecurityjwt.entity.UsersEntity;
import com.javamaster.springsecurityjwt.exception.InvalidPassWordException;
import com.javamaster.springsecurityjwt.security.JwtService;
import com.javamaster.springsecurityjwt.service.CustomUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private final CustomUserDetailService customUserDetailService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;



    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersEntity save(@RequestBody UsersEntity usersEntity){
        String passwordCrypt = passwordEncoder.encode(usersEntity.getPassword());
        usersEntity.setPassword(passwordCrypt);
        return customUserDetailService.save(usersEntity);                       
    }

    @PostMapping("/auth")
    public TokenDTO authenticate(@RequestBody Credentials credentials){
        try{
            UsersEntity user = UsersEntity.builder()
                                                .username(credentials.getUsername())
                                                .password(credentials.getPassword()).build();
            //UserDetails userAuth = customUserDetailService.auth(user);

            String token = jwtService.tokenGenerate(user);

            return new TokenDTO(user.getUsername(), token);

        }catch (UsernameNotFoundException | InvalidPassWordException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    } 


}





    

