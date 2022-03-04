package com.daewon.daewon.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    private long id;
    private String userName;
    private String userPassword;
}
