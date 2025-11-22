package com.example.demo.configuration;


import com.example.demo.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{


    @Autowired
    public JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf->csrf.disable());
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
        http.authenticationProvider(new TestAuthenticationProvider());
        http.authorizeHttpRequests(authorize->authorize.requestMatchers("/auth/user","/auth/signup","/auth/upload","/auth/login","/h2-console/**").permitAll()
              //.requestMatchers("/robot").access(a)
        .anyRequest().authenticated());
       http.oauth2Login(auth->auth.defaultSuccessUrl("/private"));

        return http.build();
    }

   /* */ @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails user = User.withUsername("rahul").password(encoder.encode("rahul")).roles("ADMIN").authorities("READ_ONLY").build();
//        UserDetails user1=User.withUsername("rakesh").password(encoder.encode("rakesh")).roles("USER").build();
//
//        return new InMemoryUserDetailsManager(user,user1);
//    }



}
