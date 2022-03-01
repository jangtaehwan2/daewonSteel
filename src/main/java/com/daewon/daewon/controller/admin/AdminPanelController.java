package com.daewon.daewon.controller.admin;

import com.daewon.daewon.domain.station.dto.StationRequestDto;
import com.daewon.daewon.domain.station.dto.StationResponseDto;
import com.daewon.daewon.domain.user.dto.CreateUserResponseDto;
import com.daewon.daewon.domain.user.dto.UserRequestDto;
import com.daewon.daewon.service.AdminPanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminPanelController {

    private final AdminPanelService adminPanelService;

    @PostMapping("/api/admin/station")
    public StationResponseDto createStation(@RequestBody StationRequestDto stationRequestDto) {
        System.out.println(stationRequestDto.toString());
        return adminPanelService.createStation(stationRequestDto.getName());
    }

//    public CreateUserResponseDto createUser(@RequestBody UserRequestDto requestDto) {
//
//    }

}
