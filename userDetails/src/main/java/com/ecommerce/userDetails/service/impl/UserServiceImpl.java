package com.ecommerce.userDetails.service.impl;

import com.ecommerce.userDetails.dto.UserDto;
import com.ecommerce.userDetails.entity.UserEntity;
import com.ecommerce.userDetails.repository.UserRepository;
import com.ecommerce.userDetails.service.UserService;
import com.ecommerce.userDetails.util.mapper.UserMapper;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = UserMapper.mapToUserEntity(userDto);
        UserEntity savedUser = userRepository.save(userEntity);
        return UserMapper.mapToUserDto(savedUser);
    }
}
