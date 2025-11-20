package com.example.demo.controller;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserService service;

    private static final Logger logger= LoggerFactory.getLogger("userController.class");

    @GetMapping("/user")
    public String greetUser()
    {
        logger.info("Exceuted Successfully");
        return "Hello,User";
    }


    @PostMapping("/signup")
    public UserModel signup(@RequestBody UserModel newUser)
    {
        logger.info("signup executed");
        return service.createUser(newUser);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto user)
    {
        logger.info("login executed");
        return service.login(user);
    }
    
    
}
