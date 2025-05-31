package com.aruimari.springboot.di.app.exceptions;

public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String message) {
        super(message);
    }
}
