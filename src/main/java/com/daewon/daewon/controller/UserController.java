package com.daewon.daewon.controller;

import com.daewon.daewon.domain.user.dto.*;
import com.daewon.daewon.service.UserService;
import com.daewon.daewon.service.AuthorizationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService adminPanelService;
    private final AuthorizationManager authorizationManager;

    @GetMapping("/user")
    public ResponseEntity<ReadUserResponseDto> readUser(@RequestHeader(value = "Authorization")String token) {
        try {
            if (authorizationManager.isAdmin(token)) {
                return ResponseEntity.ok().body(adminPanelService.readUser());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUserResponseDto> createUser(@Valid @RequestBody UserRequestDto requestDto, @RequestHeader(value = "Authorization")String token) {
        try {
            if (authorizationManager.isAdmin(token)) {
                return  ResponseEntity.ok().body(adminPanelService.createUser(requestDto.getUserName(), requestDto.getUserPassword()));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/user")
    public ResponseEntity<UpdateUserResponseDto> updateUser(@RequestBody UserRequestDto requestDto, @RequestHeader(value = "Authorization")String token) {
        try {
            if (authorizationManager.isAdmin(token)) {
                return ResponseEntity.ok().body(adminPanelService.updateUser(requestDto.getId(), requestDto.getUserPassword()));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<DeleteUserResponseDto> deleteUser(@RequestBody UserRequestDto requestDto, @RequestHeader(value = "Authorization")String token) {
        try {
            if (authorizationManager.isAdmin(token)) {
                return ResponseEntity.ok().body(adminPanelService.deleteUser(requestDto.getId()));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
