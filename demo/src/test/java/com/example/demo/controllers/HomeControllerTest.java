package com.example.demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.web.bind.annotation.GetMapping;

@TestComponent
public class HomeControllerTest {

    @Test
    @GetMapping
    public void getHomePageTest() {
    }
}


