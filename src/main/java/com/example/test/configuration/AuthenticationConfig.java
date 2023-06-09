package com.example.test.configuration;

import com.example.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    // @EnableWebSecurity를 선언함으로 써 모든 api 요청을 security가 관리하게 됨.

    private final UserService userService;

    @Value("${jwt.secret}")
    private String secretKey;

    // api 요청이 들어오면 검사하는 security의 FilterChain설정.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/users/login", "/api/v1/users/join").permitAll() // 인증 필요없음
                .requestMatchers(HttpMethod.POST, "/api/v1/**").authenticated() // 인증 있어야함
                .requestMatchers(HttpMethod.POST, "/api/v1/home/user").hasRole("USER") // USER 권한 있어야함
                .requestMatchers(HttpMethod.POST, "/api/v1/home/admin").hasRole("ADMIN") // ADMIN 권한 있어야함
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 사용하는 경우 사용
                .and()
                .addFilterBefore(new JwtFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }



}
