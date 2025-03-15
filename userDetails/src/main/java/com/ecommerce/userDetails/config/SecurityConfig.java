package com.ecommerce.userDetails.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    //creating as bean so  we can inject PasswordEncoder anywhere in the project.
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
