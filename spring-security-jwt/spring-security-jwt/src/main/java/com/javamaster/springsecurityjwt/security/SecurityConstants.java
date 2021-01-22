package com.javamaster.springsecurityjwt.security;

public class SecurityConstants {
    public static final String SECRET="Iteris";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    static final long EXPIRATION_TIME = 86400000L;


}
