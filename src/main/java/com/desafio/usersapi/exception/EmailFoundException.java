package com.desafio.usersapi.exception;

public class EmailFoundException extends RuntimeException{

    public EmailFoundException(String message) {
        super(message);
    }
}
