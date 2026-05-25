package com.example.chap04.domain.auth.dto;

import com.example.chap04.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class AuthRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class signin {
        private String name;
        private String nickname;
        private String password;
        private Member.Gender gender;
        private String email;
        private LocalDate birth;
        private Member.Address address;
        private String detailAddress;
        private String phoneNumber;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class login {
        private String email;
        private String password;
    }
}