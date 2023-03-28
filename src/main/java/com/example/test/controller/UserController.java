package com.example.test.controller;

import com.example.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok().body(userService.login("", ""));
    }

    @PostMapping("/join")
    public ResponseEntity<String> join() {
        return ResponseEntity.ok().body("회원가입 완료");
    }


}
