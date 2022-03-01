package com.daewon.daewon.service;

import com.daewon.daewon.domain.station.dto.StationResponseDto;
import com.daewon.daewon.domain.user.dto.CreateUserResponseDto;

public interface AdminPanelService {
    public StationResponseDto createStation(String name);
    public CreateUserResponseDto createUser(String userName, String userPassword);
}
