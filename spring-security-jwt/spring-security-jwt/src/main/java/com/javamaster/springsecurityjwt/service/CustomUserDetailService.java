package com.javamaster.springsecurityjwt.service;


import javax.transaction.Transactional;

import com.javamaster.springsecurityjwt.entity.UsersEntity;
import com.javamaster.springsecurityjwt.exception.InvalidPassWordException;
import com.javamaster.springsecurityjwt.repository.UsersEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService { 

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Autowired
    private UsersEntityRepository usersEntityRepository;

    @Transactional
    public UsersEntity save(UsersEntity usersEntity){
        return usersEntityRepository.save(usersEntity);
    }

    public UserDetails auth(UsersEntity usersEntity){
        UserDetails user = loadUserByUsername(usersEntity.getUsername());
        boolean passwordOk = encoder.matches(usersEntity.getPassword(), user.getPassword());
        if (passwordOk){
            return user;
        }
        throw new InvalidPassWordException();
    }

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity usersEntity = usersEntityRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        

        String[] roles = usersEntity.isAdmin() ? 
        new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User
                .builder()
                .username(usersEntity.getUsername())
                .password(usersEntity.getPassword()) //a senha já está criptografada no banco.
                .roles(roles)
                .build();                
    }
}







    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     UsersEntity user = Optional.ofNullable(usersEntityRepository.findByUsername(username))
    //             .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    //     List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
    //     List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
    //     return new org.springframework.security.core.userdetails.User
    //     (user.getUsername(), user.getPassword(), user.isAdmin() ? authorityListAdmin : authorityListUser);
    // }

