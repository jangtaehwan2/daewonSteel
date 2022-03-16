package com.daewon.daewon.controller;

import com.daewon.daewon.domain.user.dto.*;
import com.daewon.daewon.service.AdminPanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class AdminPanelController {

    private final AdminPanelService adminPanelService;

    @GetMapping("/admin/user")
    public ReadUserResponseDto readUser() {
        return adminPanelService.readUser();
    }

    @PostMapping("/admin/user")
    public CreateUserResponseDto createUser(@RequestBody UserRequestDto requestDto) {
        return adminPanelService.createUser(requestDto.getUserName(), requestDto.getUserPassword());
    }

    @PutMapping("/admin/user")
    public UpdateUserResponseDto updateUser(@RequestBody UserRequestDto requestDto) {
        return adminPanelService.updateUser(requestDto.getId(), requestDto.getUserPassword());
    }

    @DeleteMapping("/admin/user")
    public DeleteUserResponseDto deleteUser(@RequestBody UserRequestDto requestDto) {
        return adminPanelService.deleteUser(requestDto.getId());
    }
}
