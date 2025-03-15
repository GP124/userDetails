package com.ecommerce.userDetails.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDto {
    private Long userId;
    private String userName;
    private String password;
    private String email;
}
