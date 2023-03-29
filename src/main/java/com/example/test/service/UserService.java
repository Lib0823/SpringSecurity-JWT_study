package com.example.test.service;

import com.example.test.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Value("${jwt.secret}")
    private String secretKey;

    // Token 유효 시간 설정
    private Long expiredMs = 1000 * 60 * 60l;

    public String login(String userName, String password) {
        // 인증과정 생략
        log.info("userName:{}, password:{}", userName, password);
        return JwtUtil.createJwt(userName, secretKey, expiredMs);
    }

}
