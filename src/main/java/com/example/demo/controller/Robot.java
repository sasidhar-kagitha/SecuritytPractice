package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Robot {

    @GetMapping("/robot")
    public String getRobot() {
        return "Hello, Robot";
    }
}
