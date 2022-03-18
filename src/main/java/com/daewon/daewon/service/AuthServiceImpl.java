package com.daewon.daewon.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.daewon.daewon.JwtConfig;
import com.daewon.daewon.domain.auth.dto.LoginRequestDto;
import com.daewon.daewon.domain.auth.dto.LoginResponseDto;
import com.daewon.daewon.domain.user.User;
import com.daewon.daewon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @Override
    public LoginResponseDto login(String userId, String userPassword) {
        User user = userRepository.findByUserNameAndUserPassword(userId, userPassword).get();
        Algorithm algorithm = Algorithm.HMAC256(JwtConfig.Key);
        String token = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                .withIssuer(JwtConfig.issuer)
                .withClaim("userName", user.getUserName())
                .withClaim("isAdmin", user.isAdmin())
                .sign(algorithm);
        return new LoginResponseDto(token);
    }
}
