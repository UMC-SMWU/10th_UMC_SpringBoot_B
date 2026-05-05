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
        private Member.Gender gender;
        private LocalDate birth;
        private String address;
        private String detailAddress;
    }
}
