package com.example.demo.config;

import org.springframework.context.annotation.ComponentScan;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = {
    "com.example.demo.controllers",
    "com.example.demo.services"
})
public class Configuration {
    
}
