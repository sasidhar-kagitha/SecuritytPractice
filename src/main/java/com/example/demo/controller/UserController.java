package com.example.demo.controller;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/user")
    public String greetUser()
    {
        return "Hello,User";
    }


    @PostMapping("/signup")
    public UserModel signup(@RequestBody UserModel newUser)
    {
        return service.createUser(newUser);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto user)
    {
        return service.login(user);
    }
    
    
}
