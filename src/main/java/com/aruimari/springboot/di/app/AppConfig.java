package com.aruimari.springboot.di.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
public class AppConfig {
}
