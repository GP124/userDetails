package com.ecommerce.userDetails.service.impl;

import com.ecommerce.userDetails.dto.LoginRequestDto;
import com.ecommerce.userDetails.entity.UserEntity;
import com.ecommerce.userDetails.exception.ResourceNotFoundException;
import com.ecommerce.userDetails.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUser(LoginRequestDto loginRequestDto){
        UserEntity userEntity;
        userEntity = userRepository.findByUserName(loginRequestDto.userName())
                .orElseThrow(()-> new ResourceNotFoundException("User not Found"));

        boolean isPasswordMatch = passwordEncoder.matches(loginRequestDto.password(),userEntity.getPassword());
        if(!isPasswordMatch)
        {
                throw new ResourceNotFoundException("Invalid credentials");
        }
        return "Login successfull";
    }

}
