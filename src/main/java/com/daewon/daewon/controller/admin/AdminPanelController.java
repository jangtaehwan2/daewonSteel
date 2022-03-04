package com.daewon.daewon.controller.admin;

import com.daewon.daewon.domain.station.dto.StationRequestDto;
import com.daewon.daewon.domain.station.dto.StationResponseDto;
import com.daewon.daewon.domain.user.dto.*;
import com.daewon.daewon.service.AdminPanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AdminPanelController {

    private final AdminPanelService adminPanelService;

    @PostMapping("/api/admin/station")
    public StationResponseDto createStation(@RequestBody StationRequestDto stationRequestDto) {
        return adminPanelService.createStation(stationRequestDto.getName());
    }

    @GetMapping("/api/admin/user")
    public ReadUserResponseDto readUser() {
        return adminPanelService.readUser();
    }

    @PostMapping("/api/admin/user")
    public CreateUserResponseDto createUser(@RequestBody UserRequestDto requestDto) {
        return adminPanelService.createUser(requestDto.getUserName(), requestDto.getUserPassword());
    }

    @PutMapping("/api/admin/user")
    public UpdateUserResponseDto updateUser(@RequestBody UserRequestDto requestDto) {
        return adminPanelService.updateUser(requestDto.getId(), requestDto.getUserPassword());
    }

    @DeleteMapping("/api/admin/user")
    public DeleteUserResponseDto deleteUser(@RequestBody UserRequestDto requestDto) {
        return adminPanelService.deleteUser(requestDto.getId());
    }
}
