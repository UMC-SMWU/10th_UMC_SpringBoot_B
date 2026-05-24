package com.example.umc10thsb.domain.auth.dto;

import lombok.Builder;

public class AuthResDTO {

    // 로그인 응답 (JWT)
    @Builder
    public record Login(
            Long memberId,
            String email,
            String name,
            String accessToken,
            String tokenType,   // "Bearer"
            long expiresIn      // ms
    ) {}
}
