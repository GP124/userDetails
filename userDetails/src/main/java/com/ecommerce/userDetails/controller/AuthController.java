package com.ecommerce.userDetails.controller;

import com.ecommerce.userDetails.dto.LoginRequestDto;
import com.ecommerce.userDetails.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        String response = authService.authenticateUser(loginRequestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String sessionToken) {
        authService.logoutUser(sessionToken);
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/isLoggedIn")
    public ResponseEntity<Boolean> isLoggedIn(@RequestHeader("Authorization") String sessionToken) {
        boolean isLoggedIn = authService.isUserLoggedIn(sessionToken);
        return ResponseEntity.ok(isLoggedIn);
    }
}
