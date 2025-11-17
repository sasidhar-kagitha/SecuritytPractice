package com.example.demo.repository;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.model.UserModel;

public interface UserRepository {

    public UserModel createUser(UserModel userModel);
    public String login(UserLoginDto user);
}
