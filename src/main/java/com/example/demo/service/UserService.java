package com.example.demo.service;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserJpaRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService implements UserRepository {


    private final UserJpaRepository userRepo;
    private final PasswordEncoder bcrypt;
    private final JwtUtil jwt;

    UserService(UserJpaRepository userRepo,PasswordEncoder bcrypt,JwtUtil jwt)
    {
        this.userRepo=userRepo;
        this.bcrypt = bcrypt;
        this.jwt=jwt;
    }

    @Override
    public UserModel createUser(UserModel newUser) {
        String encryptedPassword = bcrypt.encode(newUser.getPassword());
        newUser.setPassword(encryptedPassword);
        return userRepo.save(newUser);
    }

    @Override
    public String login(UserLoginDto user)
    {
        try {
            UserModel existingUser = userRepo.findByUserName(user.getName());
            if (existingUser == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            if (!bcrypt.matches(user.getPassword(), existingUser.getPassword()))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid password");
            return jwt.generateToken(user.getName(),existingUser.getAuthorities());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
