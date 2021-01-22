package com.javamaster.springsecurityjwt.security;

import com.javamaster.springsecurityjwt.service.CustomUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .and()
        .csrf().disable();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }



    // @Override
    // protected void configure(HttpSecurity http) throws Exception{
    //     http.csrf().disable().authorizeRequests()
	// 	.antMatchers(HttpMethod.GET, "/").permitAll()
	// 	.anyRequest().authenticated()
	// 	.and().formLogin().permitAll()
	// 	.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    // }
    
    // @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    //     auth.inMemoryAuthentication().withUser("gabi").password("{noop}123").roles("ADMIN");
    // }
    
}
