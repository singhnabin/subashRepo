package com.nabinsingh.technovacart.exception;

public class AuthenticationFailed extends RuntimeException{
    public AuthenticationFailed(String message) {
        super(message);
    }
}
