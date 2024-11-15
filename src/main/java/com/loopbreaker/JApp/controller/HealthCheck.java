package com.loopbreaker.JApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {


    @GetMapping
    public String welcome(){
        return "<h1>Welcome to My API</h1>";
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Everything is working ok.";
    }
}
