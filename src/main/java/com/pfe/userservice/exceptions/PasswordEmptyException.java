package com.pfe.userservice.exceptions;

public class PasswordEmptyException extends RuntimeException {
    public PasswordEmptyException(String s) {
        super(s);
    }
}