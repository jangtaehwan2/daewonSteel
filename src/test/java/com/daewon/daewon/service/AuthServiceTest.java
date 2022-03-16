package com.daewon.daewon.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.daewon.daewon.JwtConfig;
import com.daewon.daewon.domain.auth.dto.LoginResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    AuthService authService;
    @Autowired
    AdminPanelService adminPanelService;
    @Autowired
    AuthorizationManager authorizationManager;


    @Test
    @DisplayName("Token Create Test")
    public void login() {
        //given
        String userName = "hello";
        String userPassword = "world";

        //when
        LoginResponseDto token = authService.login(userName, userPassword);
        Algorithm algorithm = Algorithm.HMAC256(JwtConfig.Key);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(JwtConfig.issuer)
                .build();
        DecodedJWT decodedJWT = verifier.verify(token.getKey());

        //then
        Assertions.assertThat(decodedJWT.getClaim("userName").asString()).isEqualTo(userName.toString());
    }

    @Test
    @DisplayName("general user token")
    public void general() {
        //given
        String userName = "hello";
        String userPassword = "world";
        LoginResponseDto Dto = authService.login(userName, userPassword);

        //when
        boolean isUser = authorizationManager.isUser(Dto.getKey());
        boolean isAdmin = authorizationManager.isAdmin(Dto.getKey());

        //then
        Assertions.assertThat(isUser).isEqualTo(true);
        Assertions.assertThat(isAdmin).isEqualTo(false);
    }

    @Test
    @DisplayName("admin user token")
    public void admin() {
        //given
        String userName = "JTH";
        String userPassword = "TEST";
        LoginResponseDto Dto = authService.login(userName, userPassword);

        //when
        boolean isUser = authorizationManager.isUser(Dto.getKey());
        boolean isAdmin = authorizationManager.isAdmin(Dto.getKey());

        //then
        Assertions.assertThat(isUser).isEqualTo(true);
        Assertions.assertThat(isAdmin).isEqualTo(true);
    }
}
