package com.daewon.daewon.service;

import com.daewon.daewon.domain.station.Station;
import com.daewon.daewon.domain.station.dto.StationResponseDto;
import com.daewon.daewon.domain.user.User;
import com.daewon.daewon.domain.user.dto.CreateUserResponseDto;
import com.daewon.daewon.domain.user.dto.DeleteUserResponseDto;
import com.daewon.daewon.domain.user.dto.ReadUserResponseDto;
import com.daewon.daewon.domain.user.dto.UpdateUserResponseDto;
import com.daewon.daewon.repository.StationRepository;
import com.daewon.daewon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminPanelServiceImpl implements AdminPanelService{

    private final StationRepository stationRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public StationResponseDto createStation(String name) {
        Station station = new Station(name);
        stationRepository.save(station);
        StationResponseDto stationResponseDto = new StationResponseDto(station.getId(), station.getName());
        return stationResponseDto;
    }

    @Transactional
    @Override
    public CreateUserResponseDto createUser(String userName, String userPassword) {
        User user = new User(userName, userPassword);
        userRepository.save(user);
        return new CreateUserResponseDto(user.getId(), user.getUserName());
    }

    @Transactional
    @Override
    public ReadUserResponseDto readUser() {
        return new ReadUserResponseDto(userRepository.findAll());

    }

    @Transactional
    @Override
    public UpdateUserResponseDto updateUser(long id, String newPassword) {
        User user = userRepository.findById(id).get();
        user.updatePassword(newPassword);
        userRepository.save(user);
        return new UpdateUserResponseDto(user.getId(), user.getUserName());
    }

    @Transactional
    @Override
    public DeleteUserResponseDto deleteUser(long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return new DeleteUserResponseDto("1");
    }
}
