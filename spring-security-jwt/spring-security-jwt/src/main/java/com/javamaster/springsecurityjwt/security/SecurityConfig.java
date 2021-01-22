package com.javamaster.springsecurityjwt.security;

import com.javamaster.springsecurityjwt.service.CustomUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.javamaster.springsecurityjwt.security.SecurityConstants.*;


@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.GET, SIGN_UP_URL).permitAll()
        .antMatchers("/*/tasks/**").hasRole("USER")
        .antMatchers("/*/admin/**").hasRole("ADMIN")
        .and()
        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));       
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception{
    //     http.authorizeRequests()
    //     .antMatchers("/*/tasks/**").hasRole("USER")
    //     .antMatchers("/*/admin/**").hasRole("ADMIN")
    //     .and()
    //     .httpBasic()
    //     .and()
    //     .csrf().disable();
    // }
    



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
