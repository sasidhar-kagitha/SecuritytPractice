package com.example.demo.filters;

import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.utility.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {


    private final JwtUtil jwt;

    private final CustomUserDetailsService userDetSer;

   public  JwtFilter(JwtUtil jwt,CustomUserDetailsService userDetSer)
    {
        this.jwt=jwt;
        this.userDetSer=userDetSer;
    }

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {

            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authHeader.substring(7);
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }


            String username = jwt.validateTokenAndExtractUser(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDet = userDetSer.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDet, null, userDet.getAuthorities());
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authenticationToken);
              //  SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(context);
                //filterChain.doFilter(request, response);
                Runnable r=()->System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                Thread t=new Thread(r);
                t.start();
                t.join();
                System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            }

            filterChain.doFilter(request, response);

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
