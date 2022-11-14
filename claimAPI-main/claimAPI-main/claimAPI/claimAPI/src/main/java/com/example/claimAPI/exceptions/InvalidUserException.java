package com.example.claimAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidUserException extends RuntimeException{

    private String message;

    public InvalidUserException(String message) {
        this.message = message;
    }
}
