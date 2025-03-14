package com.ecommerce.userDetails.util.mapper;

import com.ecommerce.userDetails.dto.UserDto;
import com.ecommerce.userDetails.entity.UserEntity;

public class UserMapper {
    public static UserDto mapToUserDto(UserEntity userEntity){
        return new UserDto(userEntity.getUserId(),
                userEntity.getUserName(),
                userEntity.getPassword(),
                userEntity.getEmail()
        );
    }

    public static UserEntity mapToUserEntity(UserDto userDto)
    {
        return new UserEntity(
                userDto.getUserId() != null ? userDto.getUserId() : 0L,
                userDto.getUserName(),
                userDto.getPassword(),
                userDto.getEmail()
        );
    }
}
