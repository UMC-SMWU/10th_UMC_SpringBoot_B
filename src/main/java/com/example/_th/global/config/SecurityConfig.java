package com.example._th.global.config;


import com.example._th.global.security.filter.JwtAuthFilter;
import com.example._th.global.security.service.CustomUserDetailsService;
import com.example._th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//회원가입 폼 구현 2단계 security config(비번 암호화 위한 BCrypt 등록 위해)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private final String[] allowUris = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**" // 🔥 회원가입(/auth/sign-up)이 포함된 주소! 로그인 없이 접근 가능 (Public API)
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                // 🌟 [요구사항 1] Public API와 Private API 구역 나누기
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll() // allowUris 주소는 로그인 없이 허용 (Public)
                        .anyRequest().authenticated()           // 그 외의 모든 주소는 로그인 필수 (Private)
                )

                // 폼 로그인
                .formLogin(AbstractHttpConfigurer::disable)

// 세션
                .sessionManagement(AbstractHttpConfigurer::disable)

// JWT 필터
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                // 🌟 [요구사항 2] exceptionHandling 구현으로 에러 응답 양식 통일하기!
                .exceptionHandling(exception -> exception
                        // 로그인 안 하고 잠긴 주소 찔렀을 때 1단계 장치 가동
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        // 로그인 규칙이나 권한 어긋났을 때 2단계 장치 가동
                        .accessDeniedHandler(customAccessDeniedHandler)
                );

        return http.build();

        @Bean
        public JwtAuthFilter jwtAuthFilter() {
            return new JwtAuthFilter(jwtUtil, customUserDetailsService);
        }
        @EnableWebSecurity
        @Configuration
        @RequiredArgsConstructor
        public class SecurityConfig {

            private final JwtUtil jwtUtil;
            private final CustomUserDetailsService customUserDetailsService;

        }

}