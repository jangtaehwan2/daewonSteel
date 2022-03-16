package com.daewon.daewon.service;

import com.daewon.daewon.domain.auth.dto.LoginRequestDto;
import com.daewon.daewon.domain.auth.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(String userId, String userPassword);
}
