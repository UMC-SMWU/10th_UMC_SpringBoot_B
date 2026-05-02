package com.example.chap04.domain.member.dto;

import com.example.chap04.domain.member.entity.Member;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
