package com.example.demo.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;

@Component
public class JwtUtil {

    private final long  EXPIRE_TIME = 1000*60*60;
    private final String SECRET = "jwt_secret_key-124134-3245-45342";
    private final Key key= Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities)
    {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .claim("authorites",authorities)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public String validateTokenAndExtractUser(String token)
    {
       Claims claim=Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

       return  claim.getExpiration().before(new Date())?null:claim.getSubject();

    }
}
