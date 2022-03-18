package com.daewon.daewon.controller;

import com.daewon.daewon.domain.auth.dto.LoginRequestDto;
import com.daewon.daewon.domain.auth.dto.LoginResponseDto;
import com.daewon.daewon.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        try {
            return ResponseEntity.ok().body(authService.login(requestDto.getUserId(), requestDto.getUserPassword()));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
