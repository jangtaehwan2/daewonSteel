package com.daewon.daewon.service;

import com.daewon.daewon.domain.station.dto.StationResponseDto;
import com.daewon.daewon.domain.user.dto.CreateUserResponseDto;
import com.daewon.daewon.domain.user.dto.DeleteUserResponseDto;
import com.daewon.daewon.domain.user.dto.ReadUserResponseDto;
import com.daewon.daewon.domain.user.dto.UpdateUserResponseDto;

public interface AdminPanelService {
    StationResponseDto createStation(String name);
    CreateUserResponseDto createUser(String userName, String userPassword);
    ReadUserResponseDto readUser();
    UpdateUserResponseDto updateUser(long id, String userPassword);
    DeleteUserResponseDto deleteUser(long id);


}
