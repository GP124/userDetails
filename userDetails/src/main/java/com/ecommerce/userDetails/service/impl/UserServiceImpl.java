package com.ecommerce.userDetails.service.impl;

import com.ecommerce.userDetails.dto.UserDto;
import com.ecommerce.userDetails.dto.UserRequestDto;
import com.ecommerce.userDetails.entity.UserEntity;
import com.ecommerce.userDetails.exception.ResourceNotFoundException;
import com.ecommerce.userDetails.repository.UserRepository;
import com.ecommerce.userDetails.service.UserService;
import com.ecommerce.userDetails.util.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private RedisTemplate<String, UserEntity> redisTemplate;


    @Override
    @Transactional
    public UserDto createUser(UserRequestDto userRequestDto) {

        if (userRepository.findByUserName(userRequestDto.userName()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        String hashedPassword = passwordEncoder.encode(userRequestDto.password());
        UserEntity userEntity = new UserEntity(userRequestDto.userName(), hashedPassword, userRequestDto.email());

        UserEntity savedUser = userRepository.save(userEntity);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {

        String redisKey = "USER_" +userId;
        ValueOperations<String, UserEntity> operations = redisTemplate.opsForValue();

        UserEntity cachedUser = operations.get(redisKey);
        if (cachedUser != null) {
            return UserMapper.mapToUserDto(cachedUser);
        }
        UserEntity userEntity = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User not Found" + userId));

        operations.set(redisKey, userEntity, 15, TimeUnit.SECONDS);
        return UserMapper.mapToUserDto(userEntity);
    }


}
