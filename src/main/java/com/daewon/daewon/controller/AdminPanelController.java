package com.daewon.daewon.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.daewon.daewon.domain.user.dto.*;
import com.daewon.daewon.service.AdminPanelService;
import com.daewon.daewon.service.AuthorizationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class AdminPanelController {

    private final AdminPanelService adminPanelService;
    private final AuthorizationManager authorizationManager;

    @GetMapping("/admin/user")
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

    @PostMapping("/admin/user")
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

    @PutMapping("/admin/user")
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

    @DeleteMapping("/admin/user")
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
