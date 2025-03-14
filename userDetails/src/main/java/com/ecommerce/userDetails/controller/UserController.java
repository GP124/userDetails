package com.ecommerce.userDetails.controller;

import com.ecommerce.userDetails.dto.UserDto;
import com.ecommerce.userDetails.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/userLoginDetails")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUserById (@PathVariable("userId") Long userId)
    {
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }
}
