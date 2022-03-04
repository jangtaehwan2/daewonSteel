package com.daewon.daewon.domain.user.dto;

import com.daewon.daewon.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
public class ReadUserResponseDto {
    private List<User> userList;
}
