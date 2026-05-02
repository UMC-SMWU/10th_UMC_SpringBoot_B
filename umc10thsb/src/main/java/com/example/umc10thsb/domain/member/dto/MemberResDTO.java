package com.example.umc10thsb.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    // 회원가입 응답
    @Builder
    public record SignUp(
            Long memberId,
            String email,
            String name
    ) {}

    // 마이페이지 조회, 수정 응답
    @Builder
    public record GetInfo(
            String name,
            String nickname,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {}
}
