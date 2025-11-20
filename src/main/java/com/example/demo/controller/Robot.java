package com.example.demo.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Robot {
    private static final Logger log= LoggerFactory.getLogger("Robot.class");
    @PreAuthorize("hasAuthority('WRITE_ACCESS')")
    @GetMapping("/robot")
    public String getRobot() {
        log.info("executed");
        return "Hello, Robot";
    }

    @GetMapping("/private")
    public String getPrivate(OAuth2AuthenticationToken token){return "private,page accessed"+token.getPrincipal().getAttribute("name");}
}
