package com.daewon.daewon;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.LocalDate;
import java.util.Date;

public class JwtTest {
    public static void main(String[] args) {
        String key = "secretKey";
        Date date = new Date(System.currentTimeMillis() + 5000);
        System.out.println(date);
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
        Algorithm algorithm = Algorithm.HMAC256(key);
        try {
            String token = JWT.create()
                    .withIssuer("th")
                    .withExpiresAt(date)
                    .withClaim("userName", "hello")
                    .sign(algorithm);
            System.out.println(token);

            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer("th")
                    .build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            System.out.println(decodedJWT.getExpiresAt());
            System.out.println(decodedJWT.getAlgorithm());
            System.out.println(decodedJWT.getClaims());
            System.out.println(decodedJWT.getContentType());
            System.out.println(decodedJWT.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
