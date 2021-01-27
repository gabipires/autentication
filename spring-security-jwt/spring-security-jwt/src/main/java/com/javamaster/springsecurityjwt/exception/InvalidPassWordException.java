package com.javamaster.springsecurityjwt.exception;

public class InvalidPassWordException extends RuntimeException{

    public InvalidPassWordException() {
       super("Invalid password!"); 
    }
    
}
