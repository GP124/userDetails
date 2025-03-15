package com.ecommerce.userDetails.service;

import com.ecommerce.userDetails.dto.UserDto;
import com.ecommerce.userDetails.dto.UserRequestDto;

public interface UserService {

    UserDto createUser(UserRequestDto userRequestDto);

    UserDto getUserById(Long userId);
}
