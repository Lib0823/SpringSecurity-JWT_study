package com.example.test.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    @PostMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String userPage() {
        return "userPage";
    }

    // ADMIN 권한을 가져야 접근 가능
    @PostMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminPage() {
        return "adminPage";
    }

}
