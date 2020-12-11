package com.pfe.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String s) {
        super(s);
    }
}