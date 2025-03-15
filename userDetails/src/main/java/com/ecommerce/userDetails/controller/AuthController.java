package com.ecommerce.userDetails.controller;

import com.ecommerce.userDetails.dto.LoginRequestDto;
import com.ecommerce.userDetails.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody LoginRequestDto loginRequestDto){
        String response = authService.authenticateUser(loginRequestDto);

        return ResponseEntity.ok(response);
    }
}
