package com.example.test.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static String createJwt(String userName, String secretKey, Long expiredMs) {
        Claims claims = Jwts.claims(); // username을 저장할 map?
        claims.put("userName", userName);

        return Jwts.builder() // token 생성
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

}
