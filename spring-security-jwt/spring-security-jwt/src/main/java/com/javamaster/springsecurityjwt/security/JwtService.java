package com.javamaster.springsecurityjwt.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.javamaster.springsecurityjwt.entity.UsersEntity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    @Value("${security.jwt.expirationToken}")
    private String expirationToken;

    @Value("${security.jwt.subscriptionKey}")
    private String subscriptionKey;

    public String tokenGenerate(UsersEntity usersEntity){
        long expirationTokenString = Long.valueOf(expirationToken);
        LocalDateTime dateTimeExpirated = LocalDateTime.now().plusMinutes(expirationTokenString);
        Instant instant = dateTimeExpirated.atZone(ZoneId.systemDefault()).toInstant();
        Date dateExpiration = Date.from(instant);
        return Jwts
                .builder()
                .setSubject(usersEntity.getUsername())
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS512, subscriptionKey)
                .compact();
    }

    public Claims getClaims (String token) throws ExpiredJwtException{
        return Jwts
                .parser()
                .setSigningKey(subscriptionKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValid(String token){
        try{
            Claims claims = getClaims(token);
            Date dateExpiration = claims.getExpiration();
            LocalDateTime date =
                dateExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(date);
        }catch (Exception e){
            return false;
        }
    }

    public String getUsernameUser(String token) throws ExpiredJwtException{
        return (String) getClaims(token).getSubject();
    }

}

