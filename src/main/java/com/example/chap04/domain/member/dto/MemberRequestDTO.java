package com.example.chap04.domain.member.dto;

import com.example.chap04.domain.member.entity.Member;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinRequest {
        private String name;
        private Member.Gender gender;
        private LocalDate birth;
        private Member.Address address;
        private String detailAddress;
        private String socialUid;
        private Member.SocialType socialType;
        private String email;
        private String phoneNumber;
        private List<Long> termIds;
        private List<Long> foodIds;
    }
}
