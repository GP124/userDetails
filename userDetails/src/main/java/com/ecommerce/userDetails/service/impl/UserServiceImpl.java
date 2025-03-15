package com.ecommerce.userDetails.service.impl;

import com.ecommerce.userDetails.dto.UserDto;
import com.ecommerce.userDetails.dto.UserRequestDto;
import com.ecommerce.userDetails.entity.UserEntity;
import com.ecommerce.userDetails.exception.ResourceNotFoundException;
import com.ecommerce.userDetails.repository.UserRepository;
import com.ecommerce.userDetails.service.UserService;
import com.ecommerce.userDetails.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = UserMapper.mapToUserEntity(userRequestDto);
        UserEntity savedUser = userRepository.save(userEntity);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User not Found" + userId));

        return UserMapper.mapToUserDto(userEntity);
    }
}
