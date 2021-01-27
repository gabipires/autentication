package com.javamaster.springsecurityjwt.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamaster.springsecurityjwt.service.CustomUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter {


    private JwtService jwtService;
    private CustomUserDetailService customUserDetailService;


    public JwtAuthFilter(JwtService jwtService, CustomUserDetailService customUserDetailService) {
        this.jwtService = jwtService;
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
  
                                        
        String authorization = request.getHeader("Authorization");
        
        if(authorization != null && authorization.startsWith("Bearer ")){
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.tokenValid(token);
            if (isValid){
                String usernameUser = jwtService.getUsernameUser(token);
                UserDetails user = customUserDetailService.loadUserByUsername(usernameUser);
                UsernamePasswordAuthenticationToken userToken = 
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                
                userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}
