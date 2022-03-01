package com.daewon.daewon.service;

import com.daewon.daewon.domain.station.Station;
import com.daewon.daewon.domain.station.dto.StationResponseDto;
import com.daewon.daewon.domain.user.User;
import com.daewon.daewon.domain.user.dto.CreateUserResponseDto;
import com.daewon.daewon.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminPanelServiceImpl implements AdminPanelService{

    private final StationRepository stationRepository;

    @Override
    public StationResponseDto createStation(String name) {
        Station station = new Station(name);
        stationRepository.save(station);
        StationResponseDto stationResponseDto = new StationResponseDto(station.getId(), station.getName());
        return stationResponseDto;
    }

    @Override
    public CreateUserResponseDto createUser(String userName, String userPassword) {
        return new CreateUserResponseDto(1L, "hello");
    }
}
