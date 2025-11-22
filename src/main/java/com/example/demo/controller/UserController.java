package com.example.demo.controller;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.model.UserModel;
import com.example.demo.service.S3BucketService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;


@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    S3BucketService s3Service;

    private static final Logger logger= LoggerFactory.getLogger("userController.class");

    @Value("${myapp.author}")
    private String author;


    @GetMapping("/user")
    public String greetUser()
    {
        logger.info("Exceuted Successfully");
        return "Hello,User"+" author is: "+author;
    }

    @PostMapping("/upload")
    public String getMedia(@RequestParam("file") MultipartFile file) throws Exception
    {
        logger.info("Upload Started");
        return s3Service.uploadService(file);
       /* byte[] fileData = file.getBytes();
        logger.info("name of the file: "+file.getName()+"\n Content type: "+file.getContentType()+"\n data is ");
        return "name of the file: "+file.getName()+"\n Content type: "+file.getContentType()+"\n data is "+ Arrays.toString(fileData);*/
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
