package com.ecommerce.userDetails.util.mapper;

import com.ecommerce.userDetails.dto.UserDto;
import com.ecommerce.userDetails.dto.UserRequestDto;
import com.ecommerce.userDetails.entity.UserEntity;

public class UserMapper {
    public static UserDto mapToUserDto(UserEntity userEntity){
        return new UserDto(userEntity.getUserId(),
                userEntity.getUserName(),
                userEntity.getEmail()
        );
    }

    public static UserEntity mapToUserEntity(UserRequestDto userRequestDto)
    {
        return new UserEntity(
                null,
                userRequestDto.userName(),
                userRequestDto.password(),
                userRequestDto.email()
        );
    }
}
