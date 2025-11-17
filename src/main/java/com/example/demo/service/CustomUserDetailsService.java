package com.example.demo.service;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserJpaRepository userRepo;

    CustomUserDetailsService(UserJpaRepository userRepo)
    {
        this.userRepo=userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserModel user = userRepo.findByUserName(username);
        if(user==null)
            throw new UsernameNotFoundException("user not found");
        return user;
    }


}
