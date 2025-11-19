package com.example.demo.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Robot {

    @PreAuthorize("hasAuthority('WRITE_ACCESS')")
    @GetMapping("/robot")
    public String getRobot() {
        return "Hello, Robot";
    }

    @GetMapping("/private")
    public String getPrivate(OAuth2AuthenticationToken token){return "private,page accessed"+token.getPrincipal().getAttribute("name");}
}
