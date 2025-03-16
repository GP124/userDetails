package com.ecommerce.userDetails.service.impl;

import com.ecommerce.userDetails.dto.LoginRequestDto;
import com.ecommerce.userDetails.entity.UserEntity;
import com.ecommerce.userDetails.exception.ResourceNotFoundException;
import com.ecommerce.userDetails.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String authenticateUser(LoginRequestDto loginRequestDto) {
        UserEntity userEntity;
        userEntity = userRepository.findByUserName(loginRequestDto.userName())
                .orElseThrow(() -> new ResourceNotFoundException("User not Found"));

        boolean isPasswordMatch = passwordEncoder.matches(loginRequestDto.password(), userEntity.getPassword());
        if (!isPasswordMatch) {
            throw new ResourceNotFoundException("Invalid credentials");
        }

        String sessionToken = UUID.randomUUID().toString();

        redisTemplate.opsForValue().set("SESSION_" + sessionToken,
                userEntity.getUserName(), 10, TimeUnit.SECONDS);

        return sessionToken;

    }

    public void logoutUser(String sessionToken) {
        Boolean isDeleted = redisTemplate.delete("SESSION_" + sessionToken);
        if (Boolean.FALSE.equals(isDeleted)) {
            throw new ResourceNotFoundException("Invalid session or already logged out");
        }
    }

    public boolean isUserLoggedIn(String sessionToken) {
        return redisTemplate.hasKey("SESSION_" + sessionToken);
    }
}
