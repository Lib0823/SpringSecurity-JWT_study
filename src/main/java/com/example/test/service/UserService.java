package com.example.test.service;

import com.example.test.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${jwt.secret}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60l;

    public String login(String userName, String password) {
        // 인증과정 생략
        return JwtUtil.createJwt(userName, secretKey, expiredMs);
    }

}
