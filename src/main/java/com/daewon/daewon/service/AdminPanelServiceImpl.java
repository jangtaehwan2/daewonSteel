package com.daewon.daewon.service;

import com.daewon.daewon.domain.user.User;
import com.daewon.daewon.domain.user.dto.CreateUserResponseDto;
import com.daewon.daewon.domain.user.dto.DeleteUserResponseDto;
import com.daewon.daewon.domain.user.dto.ReadUserResponseDto;
import com.daewon.daewon.domain.user.dto.UpdateUserResponseDto;
import com.daewon.daewon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminPanelServiceImpl implements AdminPanelService{

    private final UserRepository userRepository;

    @Override
    public CreateUserResponseDto createUser(String userName, String userPassword) {
        User user = new User(userName, userPassword);
        userRepository.save(user);
        return new CreateUserResponseDto(user.getId(), user.getUserName());
    }

    @Override
    public ReadUserResponseDto readUser() {
        return new ReadUserResponseDto(userRepository.findAll());

    }

    @Override
    public UpdateUserResponseDto updateUser(long id, String newPassword) {
        User user = userRepository.findById(id).get();
        user.updatePassword(newPassword);
        userRepository.save(user);
        return new UpdateUserResponseDto(user.getId(), user.getUserName());
    }

    @Override
    public DeleteUserResponseDto deleteUser(long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return new DeleteUserResponseDto("1");
    }
}
