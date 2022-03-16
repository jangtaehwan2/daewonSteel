package com.daewon.daewon.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.daewon.daewon.JwtConfig;
import com.daewon.daewon.domain.user.User;
import com.daewon.daewon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationManagerImpl implements AuthorizationManager {

    private final UserRepository userRepository;

    private Algorithm algorithm = Algorithm.HMAC256(JwtConfig.Key);
    private String issuer = JwtConfig.issuer;

    private JWTVerifier jwtVerifier = JWT.require(algorithm)
            .withIssuer(issuer)
            .build();
    @Override
    public boolean isUser(String token) {
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String userName = decodedJWT.getClaim("userName").asString();
        if(userName.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isAdmin(String token) {
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String userName = decodedJWT.getClaim("userName").asString();
        User user = userRepository.findByUserName(userName).get();
        if(user.isAdmin() == true) {
            return true;
        } else {
            return false;
        }
    }
}
