package com.basinda.exceptions;

public class AuthorizationException extends RuntimeException{

    public AuthorizationException(){
        super("Unauthorized user.");
    }

    public AuthorizationException(String message){
        super(message);
    }
}