package com.daewon.daewon.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.daewon.daewon.JwtConfig;
import com.daewon.daewon.domain.auth.dto.LoginResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.NoSuchElementException;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    AuthService authService;
    @Autowired
    UserService adminPanelService;
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
//        Assertions.assertThat(decodedJWT.getClaim("userName").asString()).isEqualTo(userName.toString());
        org.junit.jupiter.api.Assertions.assertEquals(decodedJWT.getClaim("userName").asString(), userName);
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

    @Test
    @DisplayName("valid token Test")
    public void validAndGrade() {
        //given
        String token = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() - 5000))
                .withIssuer(JwtConfig.issuer)
                .sign(Algorithm.HMAC256(JwtConfig.Key));
        LoginResponseDto AdminDto = authService.login("JTH", "TEST");
        String adminToken = AdminDto.getKey();
        LoginResponseDto GeneralDto = authService.login("hello", "world");
        String generalToken = GeneralDto.getKey();

        //when
        boolean isAdmin = authorizationManager.isAdmin(adminToken);
        boolean isGeneral = authorizationManager.isAdmin(generalToken);

        //then
        org.junit.jupiter.api.Assertions.assertEquals(isAdmin, true);
        org.junit.jupiter.api.Assertions.assertEquals(isGeneral, false);
        org.junit.jupiter.api.Assertions.assertThrows(TokenExpiredException.class, () -> authorizationManager.isAdmin(token));

    }

    @Test
    @DisplayName("Exception Test")
    public void exceptionTest() {
        //given
        String userName = "zxc";
        String userPassword = "123";

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> authService.login(userName, userPassword));
    }
}
