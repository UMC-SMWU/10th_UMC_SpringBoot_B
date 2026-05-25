package com.example.chap04.domain.member.dto;

import com.example.chap04.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class MemberResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberInfo {
        private Long memberId;
        private String name;
        private Member.Gender gender;
        private LocalDate birth;
        private Member.Address address;
        private String detailAddress;
        private Integer point;
        private String email;
        private String phoneNumber;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageResponseDto {
        private Long memberId;
        private String nickname;
        private String email;
        private String phoneNumber;
        private Boolean isPhoneVerified;
        private Integer point;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Login {
        private String accessToken;
    }
}
