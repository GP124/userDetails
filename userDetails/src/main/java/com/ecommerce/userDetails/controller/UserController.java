package com.ecommerce.userDetails.controller;

import com.ecommerce.userDetails.dto.UserDto;
import com.ecommerce.userDetails.dto.UserRequestDto;
import com.ecommerce.userDetails.service.UserService;
import com.ecommerce.userDetails.service.kafkatemplate.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/userLoginDetails")
@Slf4j
public class UserController {


    private UserService userService;
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        log.info("Creating user: {}", userRequestDto.userName());

        UserDto savedUser = userService.createUser(userRequestDto);

        String kafkaMessage = "User Created:" + savedUser.userName() + "| Email:" + savedUser.email();
        kafkaProducerService.sendMessage("user-event", kafkaMessage);
        log.info("kafka event sent:{}", kafkaMessage);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId) {
        log.info(" Fetching User by ID: {}", userId);
        UserDto userDto = userService.getUserById(userId);

        String kafkaMessage = "User Retrieved: " + userDto.userName();
        kafkaProducerService.sendMessage("user-events", kafkaMessage);
        log.info("✅ Kafka Event Sent: {}", kafkaMessage);


        return ResponseEntity.ok(userDto);
    }
}
