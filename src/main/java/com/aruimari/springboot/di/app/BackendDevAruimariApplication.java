package com.aruimari.springboot.di.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BackendDevAruimariApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendDevAruimariApplication.class, args);
    }

}
