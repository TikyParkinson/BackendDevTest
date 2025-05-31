package com.aruimari.springboot.di.app.exceptions;

public class ProductNotFoundException extends IllegalArgumentException {

    public ProductNotFoundException(String message) {
        super(message);
    }

}
